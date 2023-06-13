package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private MealService service;

    public List<Meal> getAll(int userId){
        log.info("getAll");
        return service.getAll(userId);
    }

    public Meal get(int userId, int id){
        log.info("get {}", id);
        return service.get(userId, id);
    }

    public Meal create(int userId, Meal meal){
        log.info("create {} with userid={}", meal, userId);
        checkNew(meal);
        return service.create(userId, meal);
    }

    public void update(int userId, Meal meal){
        log.info("update userId={} ; meal {}", userId, meal);
        assureIdConsistent(meal, meal.getId());
        service.update(userId, meal);
    }

}