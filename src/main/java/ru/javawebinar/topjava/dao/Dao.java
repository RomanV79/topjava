package ru.javawebinar.topjava.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    //create
    T create(T t);

    //read
    Optional<T> getById(Integer id);

    List<T> getAll();

    //update
    T update(T t);

    //delete
    void delete(Integer id);
}
