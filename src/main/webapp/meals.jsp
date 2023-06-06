<%@ page import="ru.javawebinar.topjava.model.Meal" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.javawebinar.topjava.util.MealsUtil" %>
<%@ page import="ru.javawebinar.topjava.model.MealTo" %>
<%@ page import="java.time.LocalTime" %>

<%@ page contentType="text/html;charset=UTF-8" %>

<html lang="ru">

<head>
    <style>
        table {
            border-collapse: collapse;
            border-bottom: 2px solid #000;
            border-top: 2px solid #000;
        }
        table tr td {
            border: 1px solid darkgrey;
            padding-left: 10px;
            padding-right: 10px;
        }
    </style>
    <title>Meals</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>

<%
    List<Meal> listMeal = MealsUtil.getSimpleListMeal();
    List<MealTo> listMealTo = MealsUtil.filteredByStreams(listMeal, LocalTime.of(0,0,0), LocalTime.of(23,59,59), 2000);
    request.setAttribute("listMealTo", listMealTo);
    System.out.println(listMealTo);
%>

<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${listMealTo}">
        <tr style="color:${meal.exceed ? 'red' : 'green'}">
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td>Update</td>
            <td>Delete</td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
