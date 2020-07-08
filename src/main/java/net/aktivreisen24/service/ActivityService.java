package net.aktivreisen24.service;

import net.aktivreisen24.dao.ActivityDao;
import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.User;
import net.aktivreisen24.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {
	private final ActivityDao activityDao;

	@Autowired
	public ActivityService(@Qualifier("postGREActivity") ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public long save(Activity activity) {
		return activityDao.save(activity);
	}

	public List<Activity> findAllInVacationRange(long id) {
		return activityDao.findAllInVacationRange(id);
	}

	public Optional<Activity> findByActivityId(long id) {
		return activityDao.findByActivityId(id);

	}

	public int getAddComments(Activity obj) {
		return activityDao.getAddComments(obj);
	}

	public int addComment(long id, String comment) {
		return activityDao.addComment(id, comment);
	}

}
