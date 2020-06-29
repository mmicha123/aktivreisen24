package net.aktivreisen24.service;

import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserDao userDao;

	@Autowired
	public UserService(@Qualifier("postGREUser") UserDao userDao) {
		this.userDao = userDao;
	}

	public long save(User user) {
		return userDao.save(user);
	}

}
