package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int FIRST_MEAL_ID = START_SEQ + 3;
    public static final LocalDate START_DATE = LocalDate.of(2020, Month.JANUARY, 30);
    public static final LocalDate END_DATE = LocalDate.of(2020, Month.JANUARY, 30);

    public static final List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 01, 14, 0), "Админ ланч", 510),
            new Meal(LocalDateTime.of(2015, Month.JUNE, 01, 21, 0), "Админ ужин", 1500)
    );

    public static Meal getMeal(){
        Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        meal.setId(FIRST_MEAL_ID);
        return meal;
    }

    public static Meal getNew(){
        return new Meal(LocalDateTime.of(2022, Month.FEBRUARY, 20, 11, 0), "Завтрак 2022", 511);
    }

    public static List<Meal> getBetweenInclusiveMeal(LocalDate START_DATE, LocalDate END_DATE){
        return meals.stream()
                .filter(meal -> meal.getDate().compareTo(START_DATE) >= 0
                                & meal.getDate().compareTo(END_DATE) <= 0)
                .collect(Collectors.toList());
    }

    public static List<Meal> getAllByUser(){
        List<Meal> subList = meals.subList(0,7);
        Collections.reverse(subList);
        int i = 100003;
        for (Meal meal:subList) {
            meal.setId(i);
            i++;
        }
        return subList;
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveFieldByFieldElementComparatorIgnoringFields("id").isEqualTo(expected);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("user_id").isEqualTo(expected);
    }

}
