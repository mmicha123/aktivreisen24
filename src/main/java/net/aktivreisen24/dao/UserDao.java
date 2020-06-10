package net.aktivreisen24.dao;

import net.aktivreisen24.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    int countDebug();

    long save(User obj);

    int update(User obj);

    List<User> findAll();

    Optional<User> findByUserId(long id);

    Optional<User> findByAccId(long id);

}
