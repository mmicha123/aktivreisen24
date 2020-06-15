package net.aktivreisen24.repository;

import net.aktivreisen24.dao.ActivityDao;
import net.aktivreisen24.model.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcActivityRepo implements ActivityDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of Activity in database.
     *
     * @return activities in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_activity", Integer.class);
    }

    /**
     * Save new activity in database
     *
     * @param obj Activity to save
     * @return index of this Activity
     */
    @Override
    public long save(Activity obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_activity (owner_id, address, country, price, rating, generelinfo, description) VALUES(?, ?, ?,?, ?,?,?)", new String[]{"activity_id"});
            obj.getProvider_id();
            obj.getStreet();
            obj.getCountry();
            obj.getPrice();
            obj.getRating();
            obj.getGeneralInfo();
            obj.getDescription();
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * updates the database to new Activity data
     *
     * @param obj activity to update data
     * @return number of effected rows
     */
    @Override
    public int update(Activity obj) {
        return jdbcTemplate.update("UPDATE ar_activity SET price = ?, rating = ?, generelinfo = ?, description = ? WHERE activity_id = ?",
                obj.getPrice(),
                obj.getGeneralInfo(),
                obj.getDescription(),
                obj.getId());
    }

    /**
     * list of all Activitys in database
     *
     * @return list of Activitys
     */
    @Override
    public List<Activity> findAll() {
        return jdbcTemplate.query("SELECT * FROM ar_activity", (rs, rowNum) ->
                new Activity(
                        rs.getLong("activity_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        rs.getString("generelinfo"),
                        rs.getString("description")
                ));
    }

    /**
     * list of all Activitys from one provider in database
     * @param id id off the provider
     * @return list of Activitys by provider
     */
    @Override
    public List<Activity> findAllByProviderId(long id) {
        return jdbcTemplate.query("SELECT * FROM ar_activity WHERE owner_id = ?",new Object[]{id}, (rs, rowNum) ->
                new Activity(
                        rs.getLong("activity_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        rs.getString("generelinfo"),
                        rs.getString("description")
                ));
    }

    /**
     * Find specific Activity by activity id
     *
     * @param id activity id of Activity to find
     * @return Optional Activity with this activity id
     */
    @Override
    public Optional<Activity> findByActivityId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_activity WHERE activity_id = ?", new Object[]{id}, (rs, rowNum)->
                Optional.of(new Activity(
                        rs.getLong("activity_id"),
                        rs.getLong("owner_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        rs.getString("generelinfo"),
                        rs.getString("description")
                )));
    }
}
