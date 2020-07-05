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
            return connection.prepareStatement("INSERT INTO ar_commentsupertable VALUES (DEFAULT)", new String[]{"id"});
        }, keyHolder);

        long commentId = keyHolder.getKey().longValue();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_vacation " +
                    "(owner_id, title, address, zip, city, country, price, best_season, picture_url) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", new String[]{"vacation_id"});

            ps.setLong(1, obj.getOwner_id());
            ps.setString(2, obj.getTitle());
            ps.setString(3, obj.getStreet());
            ps.setFloat(4, obj.getZipCode());
            ps.setString(5, obj.getCity());
            ps.setString(6, obj.getCountry());
            ps.setFloat(7, obj.getPrice());
            ps.setString(8, obj.getBestSeason());
            ps.setString(9, obj.getPictureUrl());

            return ps;
        }, keyHolder);

        long vacationId = keyHolder.getKey().longValue();

        jdbcTemplate.update("UPDATE ar_vacation SET comment_id = ? WHERE vacation_id = ?", commentId, vacationId);

        return vacationId;
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
     * adds a comment to a vacation
     *
     * @param obj     vacation to add comment
     * @param comment comment
     * @return number of effected rows
     */
    @Override
    public int addComment(Vacation obj, String comment) {
        return addComment(obj.getId(), comment);
    }

    /**
     * adds a comment to a vacation id
     *
     * @param objId   vacation id to add comment
     * @param comment comment
     * @return number of effected rows
     */
    @Override
    public int addComment(long objId, String comment) {
        return jdbcTemplate.update("INSERT INTO ar_comments (super_id, comment) " +
                "VALUES ((SELECT comment_id FROM ar_vacation WHERE vacation_id = ?), ?)", objId, comment);
    }

    /**
     * gets all comments and adds to the vacation
     *
     * @param obj the vacation
     * @return how many comments where added
     */
    @Override
    public int getAddComments(Vacation obj) {
        obj.setComments(jdbcTemplate.query("SELECT comment FROM ar_comments " +
                        "WHERE super_id = (SELECT comment_id FROM ar_vacation WHERE vacation_id = ?)",
                new Object[]{obj.getId()}, (rs, rowNum) ->
                        new String(rs.getString("comment"))));
        return obj.getComments().size();
    }


    /**
     * list of all Vacations in database
     *
     * @return list of Vacations
     */
    @Override
    public List<Vacation> findAll() {
        return jdbcTemplate.query("SELECT * FROM ar_vacation", (rs, rowNum) ->
                new Vacation(
                        rs.getLong("vacation_id"),
                        rs.getLong("owner_id"),
                        rs.getString("title"),
                        rs.getString("address"),
                        rs.getInt("zip"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        rs.getString("best_season"),
                        rs.getString("picture_url")
                ));
    }

    /**
     * list of all Vacations from one provider in database
     *
     * @param id id off the provider aka the account id
     * @return list of Vacations by provider
     */
    @Override
    public List<Vacation> findAllByProviderId(long id) {
        return jdbcTemplate.query("SELECT * FROM ar_vacation WHERE owner_id = ?", new Object[]{id}, (rs, rowNum) ->
                new Vacation(
                        rs.getLong("vacation_id"),
                        rs.getLong("owner_id"),
                        rs.getString("title"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating")
                ));
    }
    //TODO warum ist hier error? brauchst du denn Constructor so ?

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
                        rs.getString("title"),
                        rs.getString("address"),
                        rs.getInt("zip"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getFloat("price"),
                        rs.getFloat("rating"),
                        rs.getString("best_season")
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
