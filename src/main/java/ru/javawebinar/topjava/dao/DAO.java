package ru.javawebinar.topjava.dao;

public interface DAO<T> {
    //create
    public void save(T t);

    //read
    public T getById(Integer id);

    //update
    public void update(T t);

    //delete
    public void delete(Integer id);
}
