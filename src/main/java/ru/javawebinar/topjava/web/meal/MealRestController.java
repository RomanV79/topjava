package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    public List<Meal> getAll(Integer userId){
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(Integer userId, int id){
        log.info("get {}", id);
        return service.get(userId, id);
    }

    public Meal create(Integer userId, Meal meal){
        log.info("create {} with userid={}", meal, userId);
        return service.create(userId, meal);
    }

    public void update(Integer userId, Meal meal){
        log.info("update userId={} ; meal {}", userId, meal);
    }

}