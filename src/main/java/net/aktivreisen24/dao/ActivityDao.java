package net.aktivreisen24.dao;

import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.Vacation;

import java.util.List;
import java.util.Optional;

public interface ActivityDao {
    int countDebug();

    long save(Activity obj);

    int update(Activity obj);

    int addPicture(Activity obj, String url);

    int addPicture(long objId, String url);

    int addActivityToVacation(Activity act, Vacation vac);

    int addActivityToVacation(long actId, long vacId);

    List<Activity> findAll();

    List<Activity> findAllInVacationRange(Vacation obj);

    List<Activity> findAllInVacationRange(long id);

    List<Activity> findAllByProviderId(long id);

    Optional<Activity> findByActivityId(long id);

}
