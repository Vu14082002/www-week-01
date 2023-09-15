<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.Log" %><%--
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
</head>
<body>
<%
    String uri = request.getContextPath();
%>
<jsp:include page="../template/nav.jsp"/>
<%
    if (session.getAttribute("checkupdate") != null &&
            session.getAttribute("checkupdate").toString().equalsIgnoreCase("success")) { %>
<div class="alert alert-success alert-dismissible">
    <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    <strong>Update Success!</strong>
</div>
<%
} else if (session.getAttribute("checkupdate") != null &&
        session.getAttribute("checkupdate").toString().equalsIgnoreCase("fail")) {
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
        <h1>LIST LOG</h1>
    </div>
    <p><a href="<%=request.getContextPath()%>/log?action=add" class="link-underline-primary">Add Log</a></p>
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>Log ID</th>
            <th>account</th>
            <th>Login time</th>
            <th>Logout time</th>
            <th>Note</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Log> logs = (List<Log>) session.getAttribute("logList");
            for (Log log : logs) { %>
        <tr>
            <td><%=log.getId()%>
            </td>
            <td><%=log.getAccountId()%>
            </td>
            <td><%=log.getLoginTime()%>
            </td>
            <td><%=log.getLoginTime()%>
            </td>
            <td><%=log.getNote()%>
            </td>
            <td>
                <a class="btn btn-info"
                   href="<%=uri%>/log?action=view&id=<%=log.getId()%>">View</a>
                <a class="btn btn-primary" href="<%=uri%>/log?action=update&id=<%=log.getId()%>">Update</a>
                <a class="btn btn-danger btn-xoa" onclick="return confirm('Are you sure you want to delete?');"
                   href="<%=uri%>/log?action=delete&id=<%=log.getId()%>">Delete</a>
            </td>
        </tr>
        <%}%>
        </tbody>

    </table>
</div>
</body>
</html>
