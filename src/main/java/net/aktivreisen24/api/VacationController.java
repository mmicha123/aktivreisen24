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

import java.util.List;
import java.util.Optional;

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

	@GetMapping("/activity{id}")
	public String showSpecificActivity(@PathVariable("id") long id, Model model) {
		Optional<Activity> activity = activityService.findByActivityId(id);

		Activity test = activity.get();
		//System.out.println(activity);
		//.orElseThrow(() -> new VacationNotFoundException("Invalid activity Id:" + id));

		test.getPicturesURL().forEach(s -> System.out.println(s.toString()));

		model.addAttribute("activity", test);

		return "specificActivity";
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
