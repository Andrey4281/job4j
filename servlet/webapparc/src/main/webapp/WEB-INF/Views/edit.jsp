<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 27.11.2019
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    <label for='name'>Имя: </label><br>
    <input type="text" name="name" id="name" value="${user.name}"/><br>
    <label for='login'>Логин: </label><br>
    <input type="text" name="login" id="login" value="${user.login}"/><br>
    <label for='email'>Емайл: </label><br>
    <input type="text" name="email" id="email" value="${user.email}"/><br>
    <label for='password'>Пароль: </label><br>
    <input type="password" name="password" id="password" value="${user.password}"/><br>
    <label for='role'>Роль: </label><br>
    <select name="role" id="role">
        <c:forEach items="${roles}" var="role">
            <option value="${role.roleName}">
                <c:out value="${role.roleName}"></c:out>
            </option>
        </c:forEach>
    </select><br>
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${user.id}"/>
    Submit: <input type='submit' value='edit'/>
</form>
</body>
</html>
