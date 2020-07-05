package net.aktivreisen24.api;

import net.aktivreisen24.model.User;
import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.service.UserService;
import net.aktivreisen24.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
