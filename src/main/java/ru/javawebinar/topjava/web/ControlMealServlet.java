package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class ControlMealServlet extends HttpServlet {

    private static final Logger log = getLogger(ControlMealServlet.class);
    private final MealDao mealDao = new MealDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to add-meal");
        String action = req.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            mealDao.delete(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("meals");
        }
        if (action.equalsIgnoreCase("add")) {
            req.setAttribute("action", action);
            req.getRequestDispatcher("control.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("update")) {
            req.setAttribute("action", action);
            req.setAttribute("meal", mealDao.getById(Integer.parseInt(req.getParameter("id"))));
            req.getRequestDispatcher("control.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("add new meal");

        String action = req.getParameter("action");
        String date = req.getParameter("date");
        String description = req.getParameter("description");
        String calories = req.getParameter("calories");

        if (action.equalsIgnoreCase("add")) {
            Meal meal = new Meal(LocalDateTime.parse(date), description, Integer.parseInt(calories));
            mealDao.create(meal);
        }
        if (action.equalsIgnoreCase("update")) {
            String id = req.getParameter("id");
            Meal meal = new Meal(LocalDateTime.parse(date), description, Integer.parseInt(calories));
            meal.setId(Integer.parseInt(id));
            mealDao.update(meal);
        }

        resp.sendRedirect("meals");
    }
}
