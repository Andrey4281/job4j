<%@ page import="logic.Validate" %>
<%@ page import="logic.ValidateImpl" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 28.11.2019
  Time: 8:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Validate logic = ValidateImpl.getInstance(); %>
<html>
<head>
    <title>Main page</title>
</head>
<body>
<%List<User> users = logic.findAll();
if (users.isEmpty()) {%>
It is not exists users in system
<%} else {%>
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

    <%for (User user: users) {%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getName()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCreateDate()%></td>
        <td>
            <form action="<%=request.getContextPath()%>/" method="post">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type='submit' value='delete'/>
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/edit.jsp" method="get">
                <input type="hidden" name="id" value="<%=user.getId()%>"/>
                <input type="submit" value="edit"/>
            </form>
        </td>
    </tr>
    <%}%>
</table>
<%}%>
</body>
</html>
