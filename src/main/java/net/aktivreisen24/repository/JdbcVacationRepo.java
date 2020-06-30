package net.aktivreisen24.repository;

import net.aktivreisen24.dao.VacationDao;
import net.aktivreisen24.model.Provider;
import net.aktivreisen24.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository("postGREVacation")
public class JdbcVacationRepo implements VacationDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of Vacations in database.
     *
     * @return vacations in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_vacation", Integer.class);
    }

    /**
     * Save new vacation in database
     *
     * @param obj Vacation to save
     * @return index of this Vacation
     */
    @Override
    public long save(Vacation obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_vacation " +
                    "(owner_id, address, zip, country, price, best_season) " +
                    "VALUES (?, ?, ?, ?, ?, ?)", new String[]{"vacation_id"});
            obj.getProviderId();
            obj.getStreet();
            obj.getZipCode();
            obj.getCountry();
            obj.getPrice();
            obj.getBestSeason();
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * updates the database to new Vacation data
     *
     * @param obj vacation to update data
     * @return number of effected rows
     */
    @Override
    public int update(Vacation obj) {
        return jdbcTemplate.update("UPDATE ar_vacation SET price = ?, rating = ? WHERE vacation_id = ?",
                obj.getPrice(),
                obj.getRating(),
                obj.getId());
    }

    /**
     * list of all Vacations in database
     *
     * @return list of Vacations
     */
    @Override
    public List<Vacation> findAll() {
        return jdbcTemplate.query("SELECT * FROM ar_vacation", (rs, rowNum)->
                new Vacation(
                        rs.getLong("vacation_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating")
                ));
    }

    /**
     * list of all Vacations from one provider in database
     * @param id id off the provider aka the account id
     * @return list of Vacations by provider
     */
    @Override
    public List<Vacation> findAllByProviderId(long id) {
        return jdbcTemplate.query("SELECT * FROM ar_vacation WHERE owner_id = ?", new Object[]{id}, (rs, rowNum)->
                new Vacation(
                        rs.getLong("vacation_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating")
                ));
    }

    /**
     * Find specific Vacation by vacation id
     *
     * @param id vacation id of Vacation to find
     * @return Optional Vacation with this vacation id
     */
    @Override
    public Optional<Vacation> findByVacationId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_vacation WHERE vacation_id = ?", new Object[]{id}, (rs, rowNum) ->
                Optional.of(new Vacation(
                        rs.getLong("vacation_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating")
                )));
    }


    private long getIdCommentSuper() {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_commentsupertable VALUES(DEFAULT)", new String[]{"id"});
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
