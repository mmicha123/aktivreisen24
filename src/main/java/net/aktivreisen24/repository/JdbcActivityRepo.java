package net.aktivreisen24.repository;

import net.aktivreisen24.dao.ActivityDao;
import net.aktivreisen24.model.Activity;
import net.aktivreisen24.model.Vacation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository("postGREActivity")
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
            return connection.prepareStatement("INSERT INTO ar_commentsupertable VALUES (DEFAULT)", new String[]{"id"});
        }, keyHolder);

        long commentId = keyHolder.getKey().longValue();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_activity " +
                    "(owner_id, price, rating, description, category, need_equip, amt_people) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)", new String[]{"activity_id"});
            obj.getProvider_id();
            obj.getPrice();
            obj.getRating();
            obj.getDescription();
            obj.getCategory();
            obj.getNeededEquip();
            obj.getAmtPeople();
            return ps;
        }, keyHolder);

        long activityId = keyHolder.getKey().longValue();

        jdbcTemplate.update("UPDATE ar_activity SET comment_id = ? WHERE activity_id = ?", commentId, activityId);

        return activityId;
    }

    /**
     * updates the database to new Activity data
     *
     * @param obj activity to update data
     * @return number of effected rows
     */
    @Override
    public int update(Activity obj) {
        return jdbcTemplate.update("UPDATE ar_activity SET price = ?, rating = ?, description = ?," +
                        " description = ?, category = ?, need_equip = ?, amt_people = ? WHERE activity_id = ?",
                obj.getPrice(),
                obj.getRating(),
                obj.getDescription(),
                obj.getCategory(),
                obj.getNeededEquip(),
                obj.getAmtPeople(),
                obj.getId());
    }

    /**
     * adds a picture url to a activity
     *
     * @param obj activity to add the picture
     * @param url picture url
     * @return number of effected rows
     */
    @Override
    public int addPicture(Activity obj, String url) {
        return addPicture(obj.getId(), url);
    }

    /**
     * adds a picture url to a activity
     *
     * @param objId activity id to add the picture
     * @param url   picture url
     * @return number of effected rows
     */
    @Override
    public int addPicture(long objId, String url) {
        return jdbcTemplate.update("INSERT INTO ar_pictures(activity_id, url) VALUES (?,?)", objId, url);
    }

    /**
     * adds a comment to an activity
     *
     * @param obj     activity to add comment
     * @param comment comment
     * @return number of effected rows
     */
    @Override
    public int addComment(Activity obj, String comment) {
        return addComment(obj.getId(), comment);
    }

    /**
     * adds a comment to an activity id
     *
     * @param objId   activity id to add comment
     * @param comment comment
     * @return number of effected rows
     */
    @Override
    public int addComment(long objId, String comment) {
        return jdbcTemplate.update("INSERT INTO ar_comments(super_id, comment) " +
                "VALUES ((SELECT comment_id FROM ar_activity WHERE activity_id = ?), ?)", objId, comment);
    }

    /**
     * gets all comments and adds to the activity
     *
     * @param obj the activity
     * @return how many comments where added
     */
    @Override
    public int getAddComments(Activity obj) {
        obj.setComments(jdbcTemplate.query("SELECT comment FROM ar_comments " +
                        "WHERE super_id = (SELECT comment_id FROM ar_activity WHERE activity_id = ?)",
                new Object[]{obj.getId()}, (rs, rowNum) ->
                        new String(rs.getString("comment"))));
        return obj.getComments().size();
    }

    /**
     * gets a list of uls of pictures to a specific activity
     *
     * @param id activity id
     * @return List of urls of pictures
     */
    private List<String> findAllPictures(long id) {
        return jdbcTemplate.query("SELECT url FROM ar_pictures WHERE activity_id = ?",
                new Object[]{id}, (rs, rowNum) ->
                        new String(rs.getString("url")));
    }

    /**
     * adds an Activity to a Vacation but n to m
     *
     * @param act Activity
     * @param vac vacation
     * @return number of effected rows
     */
    @Override
    public int addActivityToVacation(Activity act, Vacation vac) {
        return addActivityToVacation(act.getId(), vac.getId());
    }

    /**
     * adds an Activity to a Vacation but n to m
     *
     * @param actId Activity id
     * @param vacId vacation id
     * @return number of effected rows
     */
    @Override
    public int addActivityToVacation(long actId, long vacId) {
        return jdbcTemplate.update("INSERT INTO ar_av_compatibility(vacation_id, activity_id) " +
                "VALUES (?, ?)", actId, vacId);
    }

    /**
     * list of all Activities in database
     *
     * @return list of Activities
     */
    @Override
    public List<Activity> findAll() {
        return jdbcTemplate.query("SELECT * FROM ar_activity", (rs, rowNum) -> {
            Activity tmp = new Activity(
                    rs.getLong("activity_id"),
                    rs.getLong("owner_id"),
                    rs.getFloat("price"),
                    rs.getFloat("rating"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getString("needEquip"),
                    rs.getInt("amt_people")
            );
            tmp.setPicturesURL(findAllPictures(rs.getLong("activity_id")));
            return tmp;
        });
    }

    /**
     * gets all Activities in Vacation range
     *
     * @param obj vacation to get the rage off
     * @return List of Activities in range of Vacation
     */
    @Override
    public List<Activity> findAllInVacationRange(Vacation obj) {
        return findAllInVacationRange(obj.getId());
    }

    /**
     * gets all Activities in Vacation range
     *
     * @param id vacation id to get the rage off
     * @return List of Activities in range of Vacation
     */
    @Override
    public List<Activity> findAllInVacationRange(long id) {
        return jdbcTemplate.query("SELECT * FROM ar_activity INNER JOIN ar_av_compatibility aac on " +
                        "ar_activity.activity_id = aac.activity_id WHERE aac.vacation_id = ?",
                new Object[]{id}, (rs, rowNum) -> {
                    Activity tmp = new Activity(
                            rs.getLong("activity_id"),
                            rs.getLong("owner_id"),
                            rs.getFloat("price"),
                            rs.getFloat("rating"),
                            rs.getString("description"),
                            rs.getString("category"),
                            rs.getString("need_equip"),
                            rs.getInt("amt_people")
                    );
                    tmp.setPicturesURL(findAllPictures(rs.getLong("activity_id")));
                    return tmp;
                }
        );
    }

    /**
     * list of all Activities from one provider in database
     *
     * @param id id off the provider
     * @return list of Activities by provider
     */
    @Override
    public List<Activity> findAllByProviderId(long id) {
        return jdbcTemplate.query("SELECT * FROM ar_activity WHERE owner_id = ?", new Object[]{id}, (rs, rowNum) -> {
                    Activity tmp = new Activity(
                            rs.getLong("activity_id"),
                            rs.getLong("owner_id"),
                            rs.getFloat("price"),
                            rs.getFloat("rating"),
                            rs.getString("description"),
                            rs.getString("category"),
                            rs.getString("need_equip"),
                            rs.getInt("amt_people")
                    );
                    tmp.setPicturesURL(findAllPictures(rs.getLong("activity_id")));
                    return tmp;
                }
        );
    }

    /**
     * Find specific Activity by activity id
     *
     * @param id activity id of Activity to find
     * @return Optional Activity with this activity id
     */
    @Override
    public Optional<Activity> findByActivityId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_activity WHERE activity_id = ?", new Object[]{id}, (rs, rowNum) -> {
            if (rowNum == 0)
                return Optional.empty();
            Activity tmp = new Activity(
                    rs.getLong("activity_id"),
                    rs.getLong("owner_id"),
                    rs.getFloat("price"),
                    rs.getFloat("rating"),
                    rs.getString("description"),
                    rs.getString("category"),
                    rs.getString("need_equip"),
                    rs.getInt("amt_people")
            );
            tmp.setPicturesURL(findAllPictures(rs.getLong("activity_id")));
            return Optional.of(tmp);
        });
    }
}
