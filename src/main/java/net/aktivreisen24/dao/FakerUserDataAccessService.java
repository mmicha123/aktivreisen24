package net.aktivreisen24.dao;

import net.aktivreisen24.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository("fakeDao")
public class FakerUserDataAccessService implements UserDao {

   private static List<User> UserList  = new ArrayList<>();

    @Override
    public int insertUser(UUID id, User user){
       UserList.add(new User(id, user.getName()));
        return 1 ;
    }




}
