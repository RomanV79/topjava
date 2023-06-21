package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThrows;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    private MealService mealService;

    @Test
    public void get() {
        Meal actual = mealService.get(USER_MEAL_ID, USER_ID);
        Meal expected = mealUser1;

        assertMatch(actual, expected);
    }

    @Test
    public void delete() {
        mealService.delete(USER_MEAL_ID, USER_ID);
        assertThrows(NotFoundException.class, () -> mealService.get(USER_MEAL_ID, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        List<Meal> actual = new ArrayList<>();
        actual.add(mealUser3);
        actual.add(mealUser2);
        actual.add(mealUser1);

        LocalDate startDate = LocalDate.of(2020, Month.JANUARY, 30);
        LocalDate endDate = LocalDate.of(2020, Month.JANUARY, 30);

        List<Meal> expected = mealService.getBetweenInclusive(startDate, endDate, USER_ID);

        assertMatch(actual, expected);
    }

    @Test
    public void getMealFilterNullBorder() {
        List<Meal> expected = Arrays.asList(mealUser7, mealUser6, mealUser5, mealUser4, mealUser3, mealUser2, mealUser1);

        assertMatch(mealService.getBetweenInclusive(null, null, USER_ID), expected);
    }

    @Test
    public void getAll() {
        List<Meal> expected = Arrays.asList(mealUser7, mealUser6, mealUser5, mealUser4, mealUser3, mealUser2, mealUser1);
        List<Meal> actual = mealService.getAll(USER_ID);

        assertMatch(actual, expected);
    }

    @Test
    public void update() {
        Meal updated = updatedMeal;
        updated.setId(USER_MEAL_ID);
        mealService.update(updated, USER_ID);

        assertMatch(mealService.get(USER_MEAL_ID, USER_ID), updatedMeal);
    }

    @Test
    public void create() {
        Meal created = mealService.create(getNewMeal(), USER_ID);
        Integer createdId = created.getId();

        Meal expected = getNewMeal();
        expected.setId(createdId);

        assertMatch(created, expected);
        assertMatch(mealService.get(createdId, USER_ID), expected);
    }

    @Test
    public void createDuplicateDateTime() {
        assertThrows(DuplicateKeyException.class, () -> mealService.create(new Meal(mealUser1.getDateTime(), "Завтрак дубль", 511), USER_ID));
    }

    // -------------- alien meal test -------------
    @Test
    public void alienMealGet() {
        assertThrows(NotFoundException.class, () -> mealService.get(USER_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void alienMealDelete() {
        assertThrows(NotFoundException.class, () -> mealService.delete(USER_MEAL_ID, ADMIN_ID));
    }

    @Test
    public void alienMealUpdate() {
        Meal updateMeal = new Meal(LocalDateTime.of(2010, Month.JANUARY, 29, 9, 0), "Завтрак новый", 501);
        updateMeal.setId(USER_MEAL_ID);
        assertThrows(NotFoundException.class, () -> mealService.update(updateMeal, ADMIN_ID));
    }

    @Test
    public void getWithFakeId() {
        assertThrows(NotFoundException.class, () -> mealService.get(FAKE_ID, USER_ID));
    }

    @Test
    public void deleteWithFakeId() {
        assertThrows(NotFoundException.class, () -> mealService.delete(FAKE_ID, USER_ID));
    }
}