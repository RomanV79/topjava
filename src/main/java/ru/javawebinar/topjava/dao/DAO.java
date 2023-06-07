package ru.javawebinar.topjava.dao;

import java.util.List;

public interface DAO<T> {
    //create
    public T create(T t);

    //read
    public T getById(Integer id);
    public List<T> getAll();

    //update
    public T update(T t);

    //delete
    public void delete(Integer id);
}
