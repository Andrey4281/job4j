<%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 26.12.2019
  Time: 15:12
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<h4>Your user:</h4>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>CreateDate</th>
        <th>Role</th>
        <th>Imagine</th>
        <th>Download Imagine</th>
        <th>Edit user</th>
    </tr>
    <tr>
        <td><c:out value="${current.id}"></c:out></td>
        <td><c:out value="${current.name}"></c:out></td>
        <td><c:out value="${current.login}"></c:out></td>
        <td><c:out value="${current.email}"></c:out></td>
        <td><c:out value="${current.createDate}"></c:out></td>
        <td><c:out value="${current.role.roleName}"></c:out></td>
        <td><img src="${pageContext.servletContext.contextPath}/download?name=${current.photoId}" width="100px" height="100px"/></td>
        <td><a href="${pageContext.servletContext.contextPath}/download?name=${current.photoId}">Download</a></td>
        <td>
            <form action="${pageContext.servletContext.contextPath}/" method="get">
                <input type="hidden" name="id" value="${current.id}"/>
                <input type="hidden" name="action" value="update"/>
                <input type="submit" value="edit"/>
            </form>
        </td>
    </tr>
</table>
<br>
<br>

<h4>All users in system:</h4>
<table style="border: 1px solid black;" cellpadding="1" cellspacing="1" border="1">
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Login</th>
        <th>Email</th>
        <th>CreateDate</th>
        <th>Role</th>
        <th>Imagine</th>
        <th>Download Imagine</th>
    </tr>

    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td><c:out value="${user.role.roleName}"></c:out></td>
            <td><img src="${pageContext.servletContext.contextPath}/download?name=${user.photoId}" width="100px" height="100px"/></td>
            <td><a href="${pageContext.servletContext.contextPath}/download?name=${user.photoId}">Download</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<br>
<form action="${pageContext.servletContext.contextPath}/signout" method="post">
    <input type="submit" value="Exit from system"/>
</form>
</body>
</html>
