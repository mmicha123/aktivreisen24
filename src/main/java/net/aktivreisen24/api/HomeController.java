package net.aktivreisen24.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	/*@Value("${welcome.message}")
	private String message;*/

	@RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
	public String showHome(Model model) {
		return "index";
	}
}
