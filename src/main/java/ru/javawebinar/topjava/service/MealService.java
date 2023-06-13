package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.security.PublicKey;
import java.util.List;

public class MealService {

    private MealRepository repository;
    public MealService(MealRepository repository){
        this.repository = repository;
    }

    public Meal create(Integer userId, Meal meal){
        return repository.save(userId, meal);
    }

    public void delete(Integer userId, int id){
        repository.delete(userId, id);
    }

    public Meal get(Integer userId, int id){
        return repository.get(userId, id);
    }

    public List<Meal> getAll(Integer userId){
        return (List<Meal>) repository.getAll(userId);
    }

    public void update(Integer userId, Meal meal){
        repository.save(userId, meal);
    }


}