package net.aktivreisen24.api;

import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.repository.JdbcVacationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SiteController {
	/*@Value("${welcome.message}")
	private String message;*/

	@Autowired
	JdbcVacationRepo jdbcVacationRepo;

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String showHome(Vacation vacation, Model model) {
		model.addAttribute("vacations", jdbcVacationRepo.findAll());
		return "index";
	}

	@RequestMapping(value = {"/blog"}, method = RequestMethod.GET)
	public String showBlog(Model model) {
		return "blog";
	}

	@RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
	public String showContact(Model model) {
		return "contact";
	}

	@RequestMapping(value = {"/addVacation"}, method = RequestMethod.GET)
	public String showAddVacation(Model model) {
		return "addVacation";
	}

	@RequestMapping(value = {"/vacations"}, method = RequestMethod.GET)
	public String showAllVacations(Model model, Vacation vacation) {
		model.addAttribute("vacations", jdbcVacationRepo.findAll());

		return "vacations";
	}
}
