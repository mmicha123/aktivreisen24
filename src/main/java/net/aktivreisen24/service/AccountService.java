package net.aktivreisen24.service;

import net.aktivreisen24.dao.AccountDao;
import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.model.Account;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

	private final AccountDao accountDao;

	@Autowired
	public AccountService(@Qualifier("postGREAccount") AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	public long save(Account account) {
		return accountDao.save(account);
	}

	public List<Account> findAll() {
		return accountDao.findAll();
	}

}
