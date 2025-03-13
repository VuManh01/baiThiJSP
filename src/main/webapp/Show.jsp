<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 3/13/2025
  Time: 8:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>Player Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #fff9f5;
        }
        h1 {
            color: #d68720;
            text-align: center;
            margin: 30px 0;
        }
        .btn-orange {
            background-color: #d68720;
            color: white;
        }
        table thead {
            background-color: #db5d42;
            color: white;
        }
        table tbody td a {
            color: #db5d42;
            margin: 0 5px;
            font-size: 18px;
        }
        footer {
            background-color: #db5d42;
            color: white;
            text-align: center;
            padding: 10px;
            margin-top: 30px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Player Information</h1>
    <c:if test="${not empty error}">
        <div class="alert alert-danger text-center">${error}</div>
    </c:if>
    <c:if test="${not empty success}">
        <div class="alert alert-success text-center">${success}</div>
    </c:if>

    <form class="row g-3 mb-4" action="player" method="post">
        <c:if test="${not empty editPlayer}">
            <input type="hidden" name="id" value="${editPlayer.id}" />
        </c:if>

        <div class="col-md-3">
            <input type="text" name="name" class="form-control" placeholder="Player name"
                   value="${editPlayer != null ? editPlayer.name : ''}">
        </div>
        <div class="col-md-3">
            <input type="text" name="age" class="form-control" placeholder="Player age"
                   value="${editPlayer != null ? editPlayer.age : ''}">
        </div>
        <div class="col-md-3">
            <select name="index_id" class="form-select">
                <option disabled ${editPlayer == null ? 'selected' : ''}>Index name</option>
                <option value="1" ${editPlayer != null && editPlayer.indexId == 1 ? 'selected' : ''}>Speed</option>
                <option value="2" ${editPlayer != null && editPlayer.indexId == 2 ? 'selected' : ''}>Strength</option>
                <option value="3" ${editPlayer != null && editPlayer.indexId == 3 ? 'selected' : ''}>Accurate</option>
            </select>
        </div>
        <div class="col-md-2">
            <select name="value" class="form-select">
                <option disabled ${editPlayer == null ? 'selected' : ''}>Value</option>
                <option value="90" ${editPlayer != null && editPlayer.value == 90 ? 'selected' : ''}>90</option>
                <option value="1" ${editPlayer != null && editPlayer.value == 1 ? 'selected' : ''}>1</option>
            </select>
        </div>
        <div class="col-md-1 d-grid">
            <button type="submit" class="btn btn-orange">
                <c:choose>
                    <c:when test="${not empty editPlayer}">Update</c:when>
                    <c:otherwise>Add</c:otherwise>
                </c:choose>
            </button>
        </div>
    </form>


    <table class="table table-bordered text-center align-middle">
        <thead>
        <tr>
            <th>Id</th>
            <th>Player name</th>
            <th>Player age</th>
            <th>Index name</th>
            <th>Value</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${players}">
            <tr>
                <td>${p.id}</td>
                <td>${p.name}</td>
                <td>${p.age}</td>
                <td>${p.indexName}</td>
                <td>${p.value}</td>
                <td>
                    <a href="player?action=edit&id=${p.id}">✏️</a>
                    <a href="player?action=delete&id=${p.id}" onclick="return confirm('Are you sure?')">🗑️</a>
                </td>

            </tr>
        </c:forEach>
        </tbody>

    </table>

    <footer>
        Số 8, Tôn Thất Thuyết, Cầu giấy, Hà Nội
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

