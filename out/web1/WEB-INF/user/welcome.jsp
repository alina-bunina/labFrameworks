<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <meta charset="UTF-8">
</head>
<body>
<h1>Welcome, <c:out value="${sessionScope.loggedUser}"/></h1>

<c:if test="${empty sessionScope.loggedUser}">
    <p>You are not logged in. Please <a href="${pageContext.request.contextPath}/login">login</a>.</p>
</c:if>
</body>
</html>
