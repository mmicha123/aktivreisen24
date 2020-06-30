package net.aktivreisen24.repository;

import net.aktivreisen24.dao.AccountDataDao;
import net.aktivreisen24.model.AccountData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.Optional;

@Repository("postGREAccountData")
public class JdbcAccountDataRepo implements AccountDataDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * For Debug purpose gets the number of AccountData entries in database.
     *
     * @return AccountData entries in database
     */
    @Override
    public int countDebug() {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM ar_account_data", Integer.class);
    }

    /**
     * Save new AccountData(User or Provider) in database
     *
     * @param obj AccountData to save
     * @return number of effected rows if 0 so error
     */
    @Override
    public long save(AccountData obj) {
        return jdbcTemplate.update("INSERT INTO ar_account_data (acc_id, first_name, last_name, phone, address, country) VALUES (?,?,?,?,?,?)",
                obj.getAcc_id(),
                obj.getFirstName(),
                obj.getLastName(),
                obj.getPhoneNumber(),
                obj.getAddress(),
                obj.getCountry());
    }

    /**
     * updates the database to new AccountData
     *
     * @param obj AccountData Object to update data
     * @return number of effected rows
     */
    @Override
    public int update(AccountData obj) {
        return jdbcTemplate.update("UPDATE ar_account_data SET first_name = ?, last_name = ?, phone = ?, address = ?, country = ? WHERE acc_id = ?",
                obj.getFirstName(),
                obj.getLastName(),
                obj.getPhoneNumber(),
                obj.getAddress(),
                obj.getCountry(),
                obj.getAcc_id());
    }

    /**
     * Find specific AccountData by its Account id
     *
     * @param id Account id of AccountData to find
     * @return Optional AccountData with this param id
     */
    @Override
    public Optional<AccountData> findByAccId(long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM ar_account_data WHERE acc_id = ?", new Object[]{id}, (rs, rowNum) ->
                Optional.of(new AccountData(
                        rs.getLong("acc_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("phone"),
                        rs.getString("address"),
                        rs.getString("country"))));
    }
}
