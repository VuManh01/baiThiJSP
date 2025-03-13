<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/13/2025
  Time: 9:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Player" %>
<%
    Player player = (Player) request.getAttribute("player");
    boolean isEdit = (player != null);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "Edit Player" : "Add Player" %></title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-5">
<h2 class="mb-4 text-center"><%= isEdit ? "Edit Player" : "Add New Player" %></h2>

<form action="player" method="post" class="row g-3">
    <input type="hidden" name="id" value="<%= isEdit ? player.getId() : "" %>">

    <div class="col-md-6">
        <label class="form-label">Player Name</label>
        <input type="text" name="name" class="form-control" required value="<%= isEdit ? player.getName() : "" %>">
    </div>

    <div class="col-md-6">
        <label class="form-label">Full Name</label>
        <input type="text" name="fullName" class="form-control" required value="<%= isEdit ? player.getFullName() : "" %>">
    </div>

    <div class="col-md-4">
        <label class="form-label">Age</label>
        <input type="text" name="age" class="form-control" required value="<%= isEdit ? player.getAge() : "" %>">
    </div>

    <div class="col-md-4">
        <label class="form-label">Index</label>
        <select name="indexId" class="form-select" required>
            <option value="1" <%= (isEdit && player.getIndexId() == 1) ? "selected" : "" %>>Speed</option>
            <option value="2" <%= (isEdit && player.getIndexId() == 2) ? "selected" : "" %>>Strength</option>
            <option value="3" <%= (isEdit && player.getIndexId() == 3) ? "selected" : "" %>>Accurate</option>
        </select>
    </div>

    <div class="col-md-4">
        <label class="form-label">Value</label>
        <input type="number" step="0.01" name="value" class="form-control" required value="<%= isEdit ? player.getValue() : "" %>">
    </div>

    <div class="col-12 text-center">
        <button type="submit" class="btn btn-success"><%= isEdit ? "Update" : "Add" %> Player</button>
        <a href="player" class="btn btn-secondary">Back</a>
    </div>
</form>
</body>
</html>