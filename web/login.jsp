<%--
  Created by IntelliJ IDEA.
  User: bunin
  Date: 31.05.2025
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <style>
        .login-form {
            max-width: 300px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .error {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="login-form">
    <h2>Login</h2>

    <c:if test="${not empty requestScope.message}">
        <c:out value="${requestScope.message}" />
    </c:if>



    <form method="post" action="${pageContext.request.contextPath}/login">
        <div class="form-group">
            <input type="text" name="login" placeholder="Enter login" required>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Enter password" required>
        </div>
        <button type="submit">Login</button>
    </form>
</div>
</body>
</html>
