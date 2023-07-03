package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private final CrudMealRepository crudRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        User user = crudUserRepository.getReferenceById(userId);
        meal.setUser(user);
        crudRepository.save(meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        crudRepository.deleteByIdAndUserId(id, userId);
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        crudRepository.findByIdAndUserId(id, userId);
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        crudRepository.findAllAndByUserId(userId);
        return null;
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        crudRepository.findByDateTimeBetweenAndByUserId(startDateTime, endDateTime, userId);
        return null;
    }
}
