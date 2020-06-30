package net.aktivreisen24.api;

import net.aktivreisen24.model.User;
import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.service.UserService;
import net.aktivreisen24.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/vacation")
@RestController
public class VacationController {

	private final VacationService vacationService;

	@Autowired
	public VacationController(VacationService vacationService) {
		this.vacationService = vacationService;
	}

	@PostMapping
	public void save(@RequestBody Vacation vacation) {
		vacationService.save(vacation);
	}
}
