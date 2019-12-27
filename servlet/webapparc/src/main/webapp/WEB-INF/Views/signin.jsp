<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 24.12.2019
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Вход в систему</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/signin" method="post">
    <c:if test="${error != ''}">
        <div style="background-color: red">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>
    <label for='login'>Логин: </label><br>
    <input type="text" name="login" id="login"/><br>
    <label for='password'>Пароль: </label><br>
    <input type="password" name="password" id="password"/><br>
    <input type='submit' value='Войти'/><br>
</form>
</body>
</html>
