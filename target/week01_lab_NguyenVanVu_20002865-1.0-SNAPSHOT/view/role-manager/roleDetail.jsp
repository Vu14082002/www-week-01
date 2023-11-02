<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.GrantAccess" %>
<%@ page import="java.util.Set" %>
<%@ page import="vn.edu.iuh.fit.enties.Role" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/11/2023
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account Detail</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>
<%
    String uri = request.getContextPath();
%>
<jsp:include page="../template/nav.jsp"/>
<% Role role = (Role) request.getAttribute("role");
    Set<GrantAccess> lisGrantAccess = role.getAccesses();
%>
<div class="container">
    <div class="row my-5">
        <h1>Account By Role</h1>
        <h3> List Account By Role ID: <%=role.getId()%></h3>
    </div>
    <table class="table table-striped table-hover table-bordered">

        <thead>
        <tr>
            <th>Account ID</th>
            <th>Account Name</th>
            <th>Account Email</th>
            <th>Account Phone</th>
        </tr>
        </thead>
        <tbody>
        <% for (GrantAccess e : lisGrantAccess) { %>
        <tr>
            <td><%=e.getAccount().getId()%></td>
            <td><%=e.getAccount().getFullName()%></td>
            <td><%=e.getAccount().getEmail()%></td>
            <td><%=e.getAccount().getPhone()%></td>
        </tr>
        <% }%>
        </tbody>
    </table>
</div>
</body>
</html>
