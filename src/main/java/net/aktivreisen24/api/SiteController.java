package net.aktivreisen24.api;

import net.aktivreisen24.model.Account;
import net.aktivreisen24.model.Vacation;
import net.aktivreisen24.repository.JdbcVacationRepo;
import net.aktivreisen24.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SiteController {
	/*@Value("${welcome.message}")
	private String message;*/

	@Autowired
	JdbcVacationRepo jdbcVacationRepo;

	private final AccountService accountService;

	public SiteController(AccountService accountService) {
		this.accountService = accountService;
	}

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String showHome(Vacation vacation, Model model) {
		model.addAttribute("vacations", jdbcVacationRepo.findAll());
		model.addAttribute("vacationsTop5", jdbcVacationRepo.findTop5());
		model.addAttribute("seasons", jdbcVacationRepo.findSeasons());
		model.addAttribute("countries", jdbcVacationRepo.findCountries());
		return "index";
	}

	@RequestMapping(value = {"/blog"}, method = RequestMethod.GET)
	public String showBlog(Model model) {
		return "order";
	}

	@RequestMapping(value = {"/contact"}, method = RequestMethod.GET)
	public String showContact(Model model) {
		return "contact";
	}

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String showLogin(Model model) {
		return "login";
	}

	@RequestMapping(value = {"/register"}, method = RequestMethod.GET)
	public String showRegister(Model model) {

		return "register";
	}

	@RequestMapping(value = {"/profile"}, method = RequestMethod.GET)
	public String showProfile(Model model) {

		return "profile";
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
