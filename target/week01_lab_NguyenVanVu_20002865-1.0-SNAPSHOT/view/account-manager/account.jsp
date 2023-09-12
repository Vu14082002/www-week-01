<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/11/2023
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Account info</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
    <script defer src="deleteAjax.js"></script>
</head>
<body>
<%
    String uri = request.getContextPath();
%>
<jsp:include page="../template/nav.jsp"/>
<%
    boolean checkUpdateSuccess = false;
    if (session.getAttribute("checkupdate") != null &&
            session.getAttribute("checkupdate").toString().equalsIgnoreCase("success")) { %>
<div class="alert alert-success alert-dismissible">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    <strong>Update Success!</strong>
</div>
<%
} else if (session.getAttribute("checkupdate") != null &&
        session.getAttribute("checkupdate").toString().equalsIgnoreCase("success")) {
%>
<div class="alert alert-danger alert-dismissible">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    <strong>Waring! something wrong</strong>
</div>
<%}%>
<br>
<br>
<br>
<div class="container">
    <div class="row">
        <h1>List Account</h1>
    </div>
    <p><a href="<%=request.getContextPath()%>/account?action=add" class="link-underline-primary">Add Account</a></p>
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>Account ID</th>
            <th>Email</th>
            <th>Full name</th>
            <th>phone</th>
            <th>status</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Account> accounts = (List<Account>) session.getAttribute("accountList");
            for (Account account : accounts) { %>
        <tr>
            <td><%=account.getId()%>
            </td>
            <td><%=account.getEmail()%>
            </td>
            <td><%=account.getFullName()%>
            </td>
            <td><%=account.getPhone()%>
            </td>
            <td><%=account.getStatus()%>
            </td>
            <td>
                <a class="btn btn-primary" href="<%=uri%>/account?action=update&id=<%=account.getId()%>">Update</a>
                <a class="btn btn-danger btn-xoa" onclick="return confirm('Are you sure you want to delete?');"
                   href="<%=uri%>/account?action=delete&id=<%=account.getId()%>">Delete</a>
                <a class="btn btn-info" role-id="<%=account.getId()%>"
                   href="<%=uri%>/account?action=view&id=<%=account.getId()%>">View</a>
            </td>
        </tr>
        <%}%>
        </tbody>

    </table>
</div>
</body>
</html>
