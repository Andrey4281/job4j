<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 27.11.2019
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name: <input type="text" name="name" value="${user.name}"/><br>
    Login: <input type="text" name="login" value="${user.login}"/><br>
    Email: <input type="text" name="email" value="${user.email}"/><br>
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="${user.id}"/>
    Submit: <input type='submit' value='edit'/>
</form>
</body>
</html>
