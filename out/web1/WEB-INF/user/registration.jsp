<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign up!</title>
    <meta charset="UTF-8">
    <style>
        .registration-form {
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
<div class="registration-form">
    <h2>Sign up!</h2>



    <form method="post" action="${pageContext.request.contextPath}/registration">
        <div class="form-group">
            <input type="text" name="login" placeholder="Enter login" required>
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Enter password" required autocomplete="off" id="passwordField" minlength="6" required>
            <span class="toggle-password" onclick="togglePassword()">
            </span>
        </div>
        <button type="submit">Sign up!</button>
    </form>
</div>
</body>
</html>
