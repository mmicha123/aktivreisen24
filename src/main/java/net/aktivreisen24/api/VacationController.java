package net.aktivreisen24.api;

import net.aktivreisen24.exceptions.VacationNotFoundException;
import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class VacationController {

	private final VacationService vacationService;

	@Autowired
	public VacationController(VacationService vacationService) {
		this.vacationService = vacationService;
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

		model.addAttribute("vacation", vacation);

		System.out.println(vacation.getPrice());
		System.out.println(vacation.getBestSeason());
		System.out.println(vacation.getCountry());
		System.out.println(vacation.getZipCode());
		System.out.println(vacation.getCity());
		System.out.println(vacation.getId());
		System.out.println(vacation.getOwner_id());
		System.out.println(vacation.getRating());

		return "vacationWithActivities";
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
