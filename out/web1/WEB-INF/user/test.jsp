<%--
  Created by IntelliJ IDEA.
  User: bunin
  Date: 09.05.2025
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<c:if test="${requestScope.getOrDefault('userCreated', false) == true}">
    <p>You can now log in</p>
</c:if>

<c:if test="${requestScope.getOrDefault('userCreated', false) == false}">
    <h2>Sign up</h2>
    <form method="post" action="${pageContext.request.contextPath}/signup">
        <p><input type="text" name="login" placeholder="enter login"></p>
        <p><input type="password" name="password" placeholder="password"></p>
        <input type="submit" value="sign up">
    </form>
</c:if>
</body>
</html>
