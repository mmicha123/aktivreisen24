package net.aktivreisen24.repository;

import net.aktivreisen24.dao.UserDao;
import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

public class JdbcUserRepo implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of Users in database.
     *
     * @return users in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_user", Integer.class);
    }

    /**
     * Save new User in database
     *
     * @param obj User to save
     * @return index of this User
     */
    @Override
    public long save(User obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_user (acc_id, first_name, last_name, phone, address, coutry) VALUES (?, ?, ?, ?, ?, ?)", new String[]{"user_id"});
            ps.setLong(1, obj.getAccId());
            ps.setString(2, obj.getFirstName());
            ps.setString(3, obj.getLastName());
            if (obj.getPhoneNumber() != 0) {
                ps.setInt(4, obj.getPhoneNumber());
            } else {
                ps.setNull(4, Types.INTEGER);
            }
            if (obj.getAddress() != null) {
                ps.setString(5, obj.getAddress());
            } else {
                ps.setNull(5, Types.VARCHAR);
            }
            if (obj.getCountry() != null) {
                ps.setString(6, obj.getCountry());
            } else {
                ps.setNull(6, Types.VARCHAR);
            }
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * updates the database to new User data
     *
     * @param obj user to update data
     * @return number of effected rows
     */
    @Override
    public int update(User obj) {
        return jdbcTemplate.update("UPDATE ar_user SET first_name = ?, last_name = ?, phone = ?, address = ?, coutry = ? WHERE user_id = ?",
                obj.getFirstName(),
                obj.getLastName(),
                obj.getPhoneNumber() != 0 ? obj.getPhoneNumber() : null,
                obj.getAddress(),
                obj.getCountry(),
                obj.getId());
    }

    /**
     * NOT IN USE
     * @return NULL
     */
    @Override
    public List<User> findAll() {
        return null;
    }

    /**
     * Find specific User by id
     * @param id user id of User to find
     * @return Optional User with this user id
     */
    @Override
    public Optional<User> findByUserId(long id) {
        return jdbcTemplate.queryForObject("SELECT * from ar_user WHERE user_id = ?", new Object[]{id}, (rs, rowNum)->
                Optional.of(new User(
                        rs.getLong("user_id"),
                        rs.getLong("acc_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getInt("phone")
                )));
    }

    /**
     * Find specific User by Account id
     * @param id acc id of User to find
     * @return Optional User with this acc id
     */
    @Override
    public Optional<User> findByAccId(long id) {
        return jdbcTemplate.queryForObject("SELECT * from ar_user WHERE acc_id = ?", new Object[]{id}, (rs, rowNum)->
                Optional.of(new User(
                        rs.getLong("user_id"),
                        rs.getLong("acc_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getInt("phone")
                )));
    }
}
