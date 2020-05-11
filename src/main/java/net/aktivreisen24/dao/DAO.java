package net.aktivreisen24.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id);
    List<T> getAll();
    void save(T t);
    void update(T t, String[] param);
    void delete(T t);
}
