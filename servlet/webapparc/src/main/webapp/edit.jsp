<%@ page import="model.User" %>
<%@ page import="logic.Validate" %>
<%@ page import="logic.ValidateImpl" %><%--
  Created by IntelliJ IDEA.
  User: andrey
  Date: 27.11.2019
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Validate logic = ValidateImpl.getInstance(); %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<%if (request.getParameter("id") == null) {%>
You should specify id of user!
<%} else {
User user = logic.findById(Integer.parseInt(request.getParameter("id")));
if (user == null) { %>
User not found!
<%} else {%>
<form action="<%=request.getContextPath()%>/" method="post">
    Name: <input type="text" name="name" value="<%=user.getName()%>"/><br>
    Login: <input type="text" name="login" value="<%=user.getLogin()%>"/><br>
    Email: <input type="text" name="email" value="<%=user.getEmail()%>"/><br>
    CreateDate: <input type="text" name="createDate" value="<%=user.getCreateDate()%>"/><br>
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="<%=user.getId()%>"/>
    Submit: <input type='submit' value='edit'/>
</form>
</body>
<% } %>
<% } %>
</html>
