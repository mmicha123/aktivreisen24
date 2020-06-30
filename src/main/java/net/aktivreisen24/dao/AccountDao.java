package net.aktivreisen24.dao;

import net.aktivreisen24.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountDao {
    int countDebug();

    long save(Account obj);

    int update(Account obj);

    int deleteById(long id);


    Optional<Account> findById(long id);

    Optional<Account> findByLogin(String pw, String mail);

}
