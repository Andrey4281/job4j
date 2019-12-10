<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 27.11.2019
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/" method="post">
    Name: <input type="text" name="name"/><br>
    Login: <input type="text" name="login"/><br>
    Email: <input type="text" name="email"/><br>
    <input type="hidden" name="action" value="add"/>
    Submit: <input type='submit' value='create'/>
</form>
</body>
</html>
