package net.aktivreisen24.service;

import net.aktivreisen24.dao.UserDao_test;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserDao_test userDaoTest;

    @Autowired
    public UserService(@Qualifier("fakeDao") UserDao_test userDaoTest) {
        this.userDaoTest = userDaoTest;
    }

    public int addUser(User user){
        return userDaoTest.insertUser(user);
    }
}
