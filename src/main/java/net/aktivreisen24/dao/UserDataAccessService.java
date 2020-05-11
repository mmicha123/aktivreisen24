package net.aktivreisen24.dao;

import net.aktivreisen24.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

public class UserDataAccessService implements DAO<User> {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Optional<User> get(int id) {
        final String sql = "SELECT * FROM ar_user WHERE user_id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{}, (resultSet, i) ->{
            return new User(
                    resultSet.getLong("user_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
        }));
    }

    @Override
    public void insert(User user) {

    }

    @Override
    public void update(User user, String[] param) {

    }

    @Override
    public void delete(User user) {

    }
}
