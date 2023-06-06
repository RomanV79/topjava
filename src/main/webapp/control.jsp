<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add meal</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>${action.equals("add") ? 'Add' : 'Update'} meal</h2>
<form method="post">
    <p>
        <label for="date">Date and time</label>
        <input type="datetime-local" name="date" id="date">
    </p>
    <p>
        <label for="description">Description</label>
        <input type="text" name="description" id="description">
    </p>
    <p>
        <label for="calories">Calories</label>
        <input type="text" name="calories" id="calories">
    </p>
    <p>
        <button type="submit">Save</button>
    </p>
</form>
<button onclick="window.history.back()" type="button">Cancel</button>


</body>
</html>
