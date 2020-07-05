package net.aktivreisen24.service;

import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.dao.VacationDao;
import net.aktivreisen24.model.User;
import net.aktivreisen24.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VacationService {

	private final VacationDao vacationDao;

	@Autowired
	public VacationService(@Qualifier("postGREVacation") VacationDao vacationDao) {
		this.vacationDao = vacationDao;
	}

	public long save(Vacation vacation) {
		return vacationDao.save(vacation);
	}

	public Optional<Vacation> findVacationByID(long id) {
		return vacationDao.findByVacationId(id);

	}

}
