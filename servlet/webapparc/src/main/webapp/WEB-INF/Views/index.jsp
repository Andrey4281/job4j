<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 28.11.2019
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>createDate</th>
        <th>Delete user</th>
        <th>Edit user</th>
    </tr>

    <c:forEach items="${users}" var="user">
    <tr>
        <td><c:out value="${user.id}"></c:out></td>
        <td><c:out value="${user.name}"></c:out></td>
        <td><c:out value="${user.login}"></c:out></td>
        <td><c:out value="${user.email}"></c:out></td>
        <td><c:out value="${user.createDate}"></c:out></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="${user.id}"/>
                <input type='submit' value='delete'/>
            </form>
        </td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/" method="get">
                <input type="hidden" name="id" value="${user.id}"/>
                <input type="hidden" name="action" value="update"/>
                <input type="submit" value="edit"/>
            </form>
        </td>
    </tr>
    </c:forEach>
</table>
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/" method="get">
    <input type="hidden" name="action" value="add"/>
    <input type="submit" value="Add new User to System"/>
</form>
</body>
</html>
