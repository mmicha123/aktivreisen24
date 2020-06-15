package net.aktivreisen24.dao;

import net.aktivreisen24.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityDao {
    int countDebug();

    long save(Activity obj);

    int update(Activity obj);

    List<Activity> findAll();

    List<Activity> findAllByProviderId(long id);

    Optional<Activity> findByActivityId(long id);

}
