package net.aktivreisen24.dao;


import net.aktivreisen24.model.AccountData;

import java.util.List;
import java.util.Optional;

public interface AccountDataDao {

    int countDebug();

    long save(AccountData obj);

    int update(AccountData obj);

    Optional<AccountData> findByAccId(long id);
}
