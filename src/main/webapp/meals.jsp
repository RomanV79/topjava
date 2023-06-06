<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<div style="margin-bottom: 20px">
    <a href="control?action=add">Add meal</a>
</div>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="meal" items="${listMealTo}">
        <tr style="color:${meal.excess ? 'red' : 'green'}">
            <td><javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="control?action=update&id=${meal.id}">Update</a></td>
            <td><a href="control?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
