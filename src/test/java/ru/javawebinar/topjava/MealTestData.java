package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_MEAL_ID = START_SEQ + 3;
    public static Meal updatedMeal = new Meal(LocalDateTime.of(2010, Month.JANUARY, 29, 9, 0), "Завтрак новый", 501);
    public static Meal duplicateDateTimeMeal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак дубль", 511);
    public static Meal meal_1 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
    public static Meal meal_2 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
    public static Meal meal_3 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
    public static Meal meal_4 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
    public static Meal meal_5 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
    public static Meal meal_6 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
    public static Meal meal_7 = new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    public static Meal meal_8 = new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510);
    public static Meal meal_9 = new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500);

    static {
        meal_1.setId(100003);
        meal_2.setId(100004);
        meal_3.setId(100005);
        meal_4.setId(100006);
        meal_5.setId(100007);
        meal_6.setId(100008);
        meal_7.setId(100009);
        meal_8.setId(100010);
        meal_9.setId(100011);
    }

//    public static final List<Meal> meals = Arrays.asList(
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
//            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410),
//            new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510),
//            new Meal(LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500)
//    );

//    public static Meal getMeal() {
//        Meal meal = new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
//        meal.setId(FIRST_MEAL_ID);
//        return meal;
//    }

    public static Meal getNewMeal() {
        return new Meal(LocalDateTime.of(2022, Month.FEBRUARY, 20, 11, 0), "Завтрак 2022", 511);
    }

//    public static List<Meal> getBetweenInclusiveMeal(LocalDate START_DATE, LocalDate END_DATE) {
//        return meals.stream()
//                .filter(meal -> meal.getDate().compareTo(START_DATE) >= 0
//                        & meal.getDate().compareTo(END_DATE) <= 0)
//                .collect(Collectors.toList());
//    }

//    public static List<Meal> getAllByUser() {
//        List<Meal> subList = meals.subList(0, 7);
//        Collections.reverse(subList);
//        int i = 100003;
//        for (Meal meal : subList) {
//            meal.setId(i);
//            i++;
//        }
//        return subList;
//    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
