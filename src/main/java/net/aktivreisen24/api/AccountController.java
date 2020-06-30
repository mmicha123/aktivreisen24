package net.aktivreisen24.api;

import net.aktivreisen24.model.Account;
import net.aktivreisen24.model.User;
import net.aktivreisen24.service.AccountService;
import net.aktivreisen24.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/account")
@RestController
public class AccountController {

	private final AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@PostMapping
	public void save(@RequestBody Account account) {
		accountService.save(account);
	}

	@GetMapping
	public List<Account> findAll() {
		return accountService.findAll();
	}

}
