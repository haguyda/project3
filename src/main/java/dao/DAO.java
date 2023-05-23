package dao;

import java.util.List;

public interface DAO<T, PK> {
    void add(T t);

    default void addAll(List<T> list) {
        list.forEach(this::add);
    }

    void update(PK pk, T t);

    void delete(PK pk);

    List<T> getAll();

    T getOne(PK pk);

    boolean isExistsById(PK pk);
}