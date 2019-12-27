<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 27.11.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/create" method="post" enctype="multipart/form-data">
    <label for='name'>Имя: </label><br>
    <input type="text" name="name" id="name"/><br>
    <label for='login'>Логин: </label><br>
    <input type="text" name="login" id="login"/><br>
    <label for='email'>Email: </label><br>
    <input type="text" name="email" id="email"/><br>
    <label for='password'>Password: </label><br>
    <input type="password" name="password" id="password"><br>
    <label for='role'>Role: </label><br>
    <select name="role" id="role">
    <c:forEach items="${roles}" var="role">
        <option value="${role.roleName}">
            <c:out value="${role.roleName}"></c:out>
        </option>
    </c:forEach>
    </select><br>
    <label for='file'>Выберите ваше изображение :</label><br>
    <input type="file" name="file" id="file" multiple accept="image/*"><br><br>
    <input type='submit' value='Создать'/>
</form>
</body>
</html>
