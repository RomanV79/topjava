package ru.javawebinar.topjava.dao;

import java.util.List;

public interface Dao<T> {
    //create
    T create(T t);

    //read
    T getById(Integer id);

    List<T> getAll();

    //update
    T update(T t);

    //delete
    void delete(Integer id);
}
