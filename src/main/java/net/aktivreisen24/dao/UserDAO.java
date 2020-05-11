package net.aktivreisen24.dao;

import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO{
    @Override
    public Optional get(int id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] param) {

    }

    @Override
    public void delete(Object o) {

    }
}
