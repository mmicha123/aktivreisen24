package net.aktivreisen24.repository;

import net.aktivreisen24.dao.AccountDao;
import net.aktivreisen24.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcAccountRepo implements AccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of Accounts in database.
     * @return Accounts in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_account", Integer.class);
    }

    /**
     * Save new Account in database
     * @param obj Account to save
     * @return index of this Account
     */
    @Override
    public long save(Account obj) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO ar_account (passhash, email) VALUES (?, ?)", new String[]{"acc_id"});
            ps.setString(1, obj.getPasshash());
            ps.setString(2, obj.getEmail());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    /**
     * updates the database to new Account data
     * @param obj Account to update data
     * @return number of effected rows
     */
    @Override
    public int update(Account obj) {
        return jdbcTemplate.update("UPDATE ar_account SET passhash = ?, email = ? WHERE acc_id = ?", obj.getPasshash(), obj.getEmail(), obj.getAcc_id());
    }

    /**
     * Delete Account and deletes the user to!!!
     * @param id the Account id to delete
     * @return number of effected rows
     */
    @Override
    public int deleteById(long id) {
        if(id <= 0) return -1;
        return jdbcTemplate.update("DELETE FROM ar_account WHERE acc_id = ?", id);
    }

    //maybe not in use so delete!!!
    /**
     * get all Accounts but not the passhash.
     * @return all Accounts (but not passhash)
     */
    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT acc_id, email FROM ar_account", (rs, rowNum) ->
                new Account(
                        rs.getLong("acc_id"),
                        null,
                        rs.getString("email")));
    }

    /**
     * Find specific Account by id
     *
     * @param id id of Account to find
     * @return Optional Account with this param id
     */
    @Override
    public Optional<Account> findById(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_account WHERE acc_id = ?", new Object[]{id}, (rs, rowNum) ->
                Optional.of(new Account(
                        rs.getLong("acc_id"),
                        rs.getString("passhash"),
                        rs.getString("email"))));
    }

    /**
     * Is used to find Account at login
     * @param pw passhash from login
     * @param mail mail from login
     * @return Optional Account check if found!
     */
    @Override
    public Optional<Account> findByLogin(String pw, String mail) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_account WHERE passhash = ? AND email = ?", new Object[]{pw, mail}, (rs, rowNum) ->
                Optional.of(new Account(
                        rs.getLong("acc_id"),
                        rs.getString("passhash"),
                        rs.getString("email"))));
    }
}
