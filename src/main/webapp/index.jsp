<%@ page import="vn.edu.iuh.fit.util.Connection" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %></h1>
<br/>
<a href="<%=request.getContextPath()%>/controllerservlet?action=login">Login</a>
<a href="<%=request.getContextPath()%>/controllerservlet?action=account">Account Info</a>
<a href="<%=request.getContextPath()%>/controllerservlet?action=role">Role Info</a>
<a href="<%=request.getContextPath()%>/controllerservlet?action=log">Log Info</a>
<a href="<%=request.getContextPath()%>/controllerservlet?action=grant-access">Grant Access Info</a>
</body>
</html>