<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.GrantAccess" %>
<%@ page import="java.util.Set" %><%--
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
<% Account account = (Account) request.getAttribute("account");
    Set<GrantAccess> lisGrantAccess = account.getGrantAccesses();
%>
<div class="container">
    <div class="row my-5">
        <h1>Roles by account</h1>
        <h3> List Role By Account ID: <%=account.getId()%></h3>
    </div>
    <table class="table table-striped table-hover table-bordered">

        <thead>
        <tr>
            <th>Role ID</th>
            <th>Role name</th>
            <th>Role Description</th>
        </tr>
        </thead>
        <tbody>
        <% for (GrantAccess e : lisGrantAccess) { %>
        <tr>
            <td><%=e.getRole().getId()%></td>
            <td><%=e.getRole().getName()%></td>
            <td><%=e.getRole().getDescription()%></td>
        </tr>
        <% }%>
        </tbody>
    </table>
</div>
</body>
</html>
