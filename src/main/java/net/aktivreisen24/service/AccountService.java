package net.aktivreisen24.service;

import net.aktivreisen24.dao.AccountDao;
import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.model.Account;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Account> findByLogin(String pw, String mail) {
		return accountDao.findByLogin(pw, mail);
	}

	/*public List<Account> findAll() {
		//return accountDao.findAll();
	}*/

}
