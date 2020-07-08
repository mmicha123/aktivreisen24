package net.aktivreisen24.api;

import net.aktivreisen24.exceptions.VacationNotFoundException;
import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.service.ActivityService;
import net.aktivreisen24.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class VacationController {

	private final VacationService vacationService;
	private final ActivityService activityService;

	@Autowired
	public VacationController(VacationService vacationService, ActivityService activityService) {
		this.vacationService = vacationService;
		this.activityService = activityService;
	}

	@PostMapping("/api/vacation")
	public void save(@RequestBody Vacation vacation) {
		vacationService.save(vacation);
	}

	@PostMapping(value = {"/addVacation/add"})
	public String saveFromWebsite(@ModelAttribute Vacation vacation, Model model) {
		vacationService.save(vacation);
		System.out.println(model);
		return "redirect:/addVacation";
	}

	@GetMapping("/vacation{id}")
	public String showSpecificVacation(@PathVariable("id") long id, Model model) {
		Vacation vacation = vacationService.findVacationByID(id)
				.orElseThrow(() -> new VacationNotFoundException("Invalid journey Id:" + id));

		List<Activity> activitiesForSpecificVacation = activityService.findAllInVacationRange(id);

		model.addAttribute("vacation", vacation);
		model.addAttribute("activities", activitiesForSpecificVacation);

		return "vacationWithActivities";
	}

	@GetMapping("/vacationsByFilter")
	public String showFilteredVacations(@RequestParam("bestSeason") String bestSeason, @RequestParam("country") String country, @RequestParam("priceFrom") Float priceFrom, @RequestParam("priceTo") Float priceTo, Model model) {
		List<Vacation> filteredVacations = vacationService.findVacationsByFilter(bestSeason, country, priceFrom, priceTo);

		model.addAttribute("vacations", filteredVacations);

		System.out.println(filteredVacations.get(0).getCity());

		return "vacations";
	}

	public static Date between(Date startInclusive, Date endExclusive) {
		long startMillis = startInclusive.getTime();
		long endMillis = endExclusive.getTime();
		long randomMillisSinceEpoch = ThreadLocalRandom
				.current()
				.nextLong(startMillis, endMillis);

		return new Date(randomMillisSinceEpoch);
	}

	@GetMapping("/activity{id}")
	public String showSpecificActivity(@PathVariable("id") long id, Model model) {
		Optional<Activity> activity = activityService.findByActivityId(id);
		Activity test = activity.get();

		activityService.getAddComments(test);

		List<String> comments = test.getComments();

		//System.out.println(activity);
		//.orElseThrow(() -> new VacationNotFoundException("Invalid activity Id:" + id));

		test.getPicturesURL().forEach(s -> System.out.println(s.toString()));

		model.addAttribute("activity", test);
		model.addAttribute("comments", comments);

		return "specificActivity";
	}

	@GetMapping(value = {"/showVacationOrder"})
	public String showVacationOrder(@RequestParam("vacation") long vacationID, @RequestParam("checkInDate") String checkInDate, @RequestParam("checkOutDate") String checkOutDate, @RequestParam("activity1") long id1, @RequestParam("activity2") long id2, @RequestParam("activity3") long id3, @RequestParam("activity4") long id4, Model model) {

		Vacation vacation = vacationService.findVacationByID(vacationID).orElseThrow(VacationNotFoundException::new);
		Activity activity1 = activityService.findByActivityId(id1).orElseThrow(VacationNotFoundException::new);
		Activity activity2 = activityService.findByActivityId(id2).orElseThrow(VacationNotFoundException::new);
		Activity activity3 = activityService.findByActivityId(id3).orElseThrow(VacationNotFoundException::new);
		Activity activity4 = activityService.findByActivityId(id4).orElseThrow(VacationNotFoundException::new);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LocalDate checkIn = LocalDate.parse(checkInDate, dtf);
		LocalDate checkOut = LocalDate.parse(checkOutDate, dtf);

		Date cI = null;
		Date cO = null;
		try {
			cI = new SimpleDateFormat("yyyy-MM-dd").parse(checkInDate);
			cO = new SimpleDateFormat("yyyy-MM-dd").parse(checkOutDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);

		List<Integer> randomInts = new ArrayList<>();
		List<LocalDate> randomDates = new ArrayList<>();

		ThreadLocalRandom.current().ints(1, (int) (daysBetween - 1)).distinct().limit(4).forEach(value -> randomInts.add(value));

		for (Integer a : randomInts
		) {

			randomDates.add(checkIn.plusDays(a));

		}

		model.addAttribute("activity1", activity1);
		model.addAttribute("activity2", activity2);
		model.addAttribute("activity3", activity3);
		model.addAttribute("activity4", activity4);
		model.addAttribute("randomDate1", randomDates.get(0));
		model.addAttribute("randomDate2", randomDates.get(1));
		model.addAttribute("randomDate3", randomDates.get(2));
		model.addAttribute("randomDate4", randomDates.get(3));
		model.addAttribute("vacation", vacation);
		model.addAttribute("checkInDate", checkInDate);
		model.addAttribute("checkOutDate", checkOutDate);
		model.addAttribute("holidayDays", daysBetween);

		return "order";
	}

	@PostMapping(value = {"/addComment"})
	public String saveFromWebsite(@RequestParam(name = "activityID") long id, @RequestParam(name = "ownComment") String ownComment, Model model) {

		System.out.println(id);

		activityService.addComment(id, ownComment);
		return "redirect:/addVacation";
	}



	/*@PostMapping("/api/addVacation/add")
	public String addVacation(@Valid Vacation vacation, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "addVacation";
		}

		vacationService.save(vacation);
		//model.addAttribute("journeys", journeyRepository.findAll());
		return "addVacation";
	}*/

}
