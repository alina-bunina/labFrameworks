<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.Pet" %>

<html>
<head>
    <title>Питомцы пользователя</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            padding: 30px;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: center;
        }
        th {
            background-color: #f2f2f2;
        }
        input[type="text"], input[type="number"] {
            width: 90%;
            padding: 5px;
            box-sizing: border-box;
        }
        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            color: white;
            cursor: pointer;
        }
        .btn-save { background-color: #4CAF50; }
        .btn-delete { background-color: #f44336; }
        .btn-add { background-color: #2196F3; margin-top: 10px; }
        .btn:hover { opacity: 0.9; }
        .error { color: red; margin-top: 10px; }
        .add-form {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
            max-width: 300px;
        }
        .add-form label {
            margin-bottom: 10px;
        }
        .add-form input {
            margin-top: 5px;
        }
    </style>
</head>
<body>

<h1>Ваши питомцы</h1>

<form method="get" action="${pageContext.request.contextPath}/pets" style="margin-top: 20px; margin-bottom: 20px;">
    <input type="text" name="search" placeholder="Введите имя питомца" value="${param.search}" maxlength="50"
           style="padding: 8px; width: 250px;" />
    <button type="submit" class="btn btn-add">Найти</button>
</form>

<% String error = (String) request.getAttribute("error");
    if (error != null) { %>
<div class="error"><%= error %></div>
<% } %>

<!-- Форма добавления нового питомца -->
<form method="post" action="${pageContext.request.contextPath}/pets" class="add-form">
    <input type="hidden" name="action" value="add" />

    <label>Имя:
        <input type="text" name="pet" maxlength="50" required />
    </label>

    <label>Возраст:
        <input type="number" name="age" min="0" max="100" required />
    </label>

    <button type="submit" class="btn btn-add">Добавить</button>
</form>


<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Имя</th>
        <th>Возраст</th>
        <th>Действия</th>
    </tr>
    </thead>
    <tbody>
    <%
        List<Pet> pets = (List<Pet>) request.getAttribute("pets");
        if (pets != null && !pets.isEmpty()) {
            for (Pet pet : pets) {
    %>
    <tr>
        <form method="post" action="${pageContext.request.contextPath}/pets">
            <td><%= pet.getId() %>
                <input type="hidden" name="id_pet" value="<%= pet.getId() %>" />
            </td>
            <td><input type="text" name="pet" value="<%= pet.getPet() %>" maxlength="50" required /></td>
            <td><input type="number" name="age" value="<%= pet.getAge() %>" min="0" max="100" required /></td>
            <td>
                <input type="hidden" name="action" value="update" />
                <button type="submit" class="btn btn-save">Сохранить</button>
        </form>
        <form method="post" action="${pageContext.request.contextPath}/pets" style="display:inline;">
            <input type="hidden" name="id_pet" value="<%= pet.getId() %>" />
            <input type="hidden" name="action" value="delete" />
            <button type="submit" class="btn btn-delete">Удалить</button>
        </form>
        </td>
    </tr>
    <%
        }
    } else {
    %>
    <tr><td colspan="4">У вас пока нет питомцев.</td></tr>
    <%
        }
    %>
    </tbody>
</table>

<form action="<%= request.getContextPath() + "/logout" %>" method="get">
    <button type="submit" class="btn btn-add">Выйти</button>
</form>

</body>
</html>



