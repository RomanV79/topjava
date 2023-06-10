package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private final MealDao mealDao = new MealDao();
    private static final int CALORIES_PER_DAY_LIMIT = 2000;
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to meals");
        List<Meal> listMeal = mealDao.getAll();
        List<MealTo> listMealTo = MealsUtil.filteredByStreams(listMeal, LocalTime.MIN, LocalTime.MAX, CALORIES_PER_DAY_LIMIT);
        req.setAttribute("listMealTo", listMealTo);
        req.getRequestDispatcher("meals.jsp").forward(req, resp);
    }
}
