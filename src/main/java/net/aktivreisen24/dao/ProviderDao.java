package net.aktivreisen24.dao;

import net.aktivreisen24.model.Provider;
import net.aktivreisen24.model.User;

import java.util.List;
import java.util.Optional;

public interface ProviderDao {
    int countDebug();

    long save(Provider obj);

    int update(Provider obj);

    List<Provider> findAll();

    Optional<Provider> findByProviderId(long id);

    Optional<Provider> findByAccId(long id);
}
