<%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/11/2023
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HOME PAGE</title>
</head>
<body>
<%
    String username=null;
    try{
        if (request.getSession().getAttribute("username") == null) {
            response.sendRedirect(request.getContextPath() + "/controllerservlet?action=login");
        }
        username = request.getSession().getAttribute("username").toString();
    }catch ( Exception e){
        response.sendRedirect(request.getContextPath() + "/controllerservlet?action=login");
    }
%>
Username: <%=username%>
<form action="<%=request.getContextPath()%>/controllerservlet" method="post">
    <input type="submit" value="Logout">
    <input type="hidden" name="action" value="logoutsubmit">
</form>
</body>
</html>
