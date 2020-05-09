package net.aktivreisen24.dao;

import net.aktivreisen24.model.User;

import java.util.UUID;

public interface UserDao {

    int insertUser(UUID id, User user);

    default int insertUser(User user){
        UUID id = UUID.randomUUID();
        return insertUser(id, user);
    }
}
