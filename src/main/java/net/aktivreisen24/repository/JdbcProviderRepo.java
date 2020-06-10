package net.aktivreisen24.repository;

import net.aktivreisen24.dao.ProviderDao;
import net.aktivreisen24.model.Provider;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProviderRepo implements ProviderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of Providers in database.
     *
     * @return number of Provider in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_provider", Integer.class);
    }

    /**
     * Save new Provider in database
     *
     * @param obj Provider to save
     * @return index of this Provider
     */
    @Override
    public long save(Provider obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_provider (acc_id, name, rating) VALUES (?, ?, ?)", new String[]{"provider_id"});
            ps.setLong(1, obj.getAccId());
            ps.setString(2, obj.getName());
            ps.setFloat(3, obj.getRating());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * updates the database to new Provider data
     *
     * @param obj provider to update data
     * @return number of effected rows
     */
    @Override
    public int update(Provider obj) {
        return jdbcTemplate.update("UPDATE ar_provider SET name = ?, rating = ? WHERE provider_id = ?", obj.getName(), obj.getRating(), obj.getId());
    }

    /**
     * NOT IN USE
     *
     * @return NULL
     */
    @Override
    public List<Provider> findAll() {
        return null;
    }
    /**
     * Find specific Provider by id
     *
     * @param id user id of Provider to find
     * @return Optional Provider with this provider id
     */
    @Override
    public Optional<Provider> findByProviderId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_provider WHERE provider_id = ?", new Object[]{id},(rs, rowNum) ->
                Optional.of(new Provider(
                        rs.getLong("provider_id"),
                        rs.getLong("acc_id"),
                        rs.getString("name"),
                        rs.getFloat("rating")
                )));
    }

    /**
     * Find specific Provider by Account id
     *
     * @param id acc id of Provider to find
     * @return Optional Provider with this acc id
     */
    @Override
    public Optional<Provider> findByAccId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_provider WHERE acc_id = ?", new Object[]{id},(rs, rowNum) ->
                Optional.of(new Provider(
                        rs.getLong("provider_id"),
                        rs.getLong("acc_id"),
                        rs.getString("name"),
                        rs.getFloat("rating")
                )));
    }
}
