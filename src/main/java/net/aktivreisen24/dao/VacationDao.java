package net.aktivreisen24.dao;

import net.aktivreisen24.model.Provider;
import net.aktivreisen24.model.Vacation;

import java.util.List;
import java.util.Optional;

public interface VacationDao {
    int countDebug();

    long save(Vacation obj);

    int update(Vacation obj);

    List<Vacation> findAll();

    List<Vacation> findAllByProviderId(long id);

    Optional<Vacation> findByVacationId(long id);

    Optional<Vacation> findByProviderId(long id);


}
