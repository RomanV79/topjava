package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

public class MealDAO implements DAO<Meal>{
    @Override
    public void save(Meal meal) {
        MealsUtil.mapMealDB.put(meal.getId(), meal);
    }

    @Override
    public Meal getById(Integer id) {
        return MealsUtil.mapMealDB.get(id);
    }

    @Override
    public void update(Meal meal) {
        MealsUtil.mapMealDB.put(meal.getId(), meal);
    }

    @Override
    public void delete(Integer id) {
        MealsUtil.mapMealDB.remove(id);
    }
}
