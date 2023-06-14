package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;


@Controller
public class MealRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAll(){
        log.info("getAll");
        return service.getAll(authUserId());
    }

    public Meal get(int id){
        log.info("get {}", id);
        return service.get(authUserId(), id);
    }

    public Meal create(Meal meal){
        log.info("create {} with userid={}", meal, authUserId());
        checkNew(meal);
        return service.create(authUserId(), meal);
    }

    public void update(Meal meal, int id){
        log.info("update userId={} ; meal {}", authUserId(), meal);
        assureIdConsistent(meal, id);
        service.update(authUserId(), meal);
    }

}