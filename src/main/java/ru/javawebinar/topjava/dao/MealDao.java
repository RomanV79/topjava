package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MealDao implements DAO<Meal>{
    public static final Map<Integer, Meal> mapMealDB = new ConcurrentHashMap();
    static {
        MealDao mealDao = new MealDao();
        Meal meal1= new  Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        Meal meal2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        Meal meal3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        Meal meal4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        Meal meal5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        Meal meal6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        Meal meal7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 700);
        mealDao.create(meal1);
        mealDao.create(meal2);
        mealDao.create(meal3);
        mealDao.create(meal4);
        mealDao.create(meal5);
        mealDao.create(meal6);
        mealDao.create(meal7);
    }
    @Override
    public Meal create(Meal meal) {
        Integer id = CountHolder.getCount();
        mapMealDB.put(id, meal);
        meal.setId(id);
        return meal;
    }

    @Override
    public Meal getById(Integer id) {
        return mapMealDB.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList(mapMealDB.values());
    }

    @Override
    public Meal update(Meal meal) {
        mapMealDB.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public void delete(Integer id) {
        mapMealDB.remove(id);
    }
}
