package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;

import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal actual = mealService.get(FIRST_MEAL_ID, USER_ID);
        Meal expected = getMeal();
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    @Test
    public void delete() {
        mealService.delete(FIRST_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(FIRST_MEAL_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actualInclusive = mealService.getBetweenInclusive(START_DATE, END_DATE, USER_ID);
        List<Meal> expectedInclusive = getBetweenInclusiveMeal(START_DATE, END_DATE);
        assertMatch(actualInclusive, expectedInclusive);
    }

    @Test
    public void getAll() {
        List<Meal> actualMeals = mealService.getAll(USER_ID);
        List<Meal> expectedMeals = getAllByUser();
        assertMatch(actualMeals, expectedMeals);
    }

    @Test
    public void update() {
        Meal updateMeal = new Meal(LocalDateTime.of(2010, Month.JANUARY, 29, 9, 0), "Завтрак новый", 501);
        updateMeal.setId(FIRST_MEAL_ID);
        mealService.update(updateMeal, USER_ID);
        Meal expectedMeal = mealService.get(FIRST_MEAL_ID, USER_ID);
        assertMatch(updateMeal, expectedMeal);
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNew(), USER_ID);
        Integer createdId = created.getId();
        Meal expected = getNew();
        expected.setId(createdId);
        assertMatch(created, expected);
    }

    // alien meal test
    @Test
    public void alienMealGet(){
        assertThrows(NotFoundException.class, () -> mealService.get(FIRST_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void alienMealDelete(){
        assertThrows(NotFoundException.class, () -> mealService.delete(FIRST_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void alienMealUpdate(){
        Meal updateMeal = new Meal(LocalDateTime.of(2010, Month.JANUARY, 29, 9, 0), "Завтрак новый", 501);
        updateMeal.setId(FIRST_MEAL_ID);
        assertThrows(NotFoundException.class, () -> mealService.update(updateMeal, ADMIN_ID));
    }




}