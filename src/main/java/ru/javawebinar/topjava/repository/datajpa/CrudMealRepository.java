package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    void deleteByIdAndUserId(int id, int userId);

    void findByIdAndUserId(int id, int userId);

    void findAllAndByUserId(int userId);

    void findByDateTimeBetweenAndByUserId(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId);
}
