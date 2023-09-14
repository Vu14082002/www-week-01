<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %>
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
        <h1>List Role</h1>
    </div>
    <p><a href="<%=request.getContextPath()%>/role?action=add" class="link-underline-primary">Add Role</a></p>
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>Role ID</th>
            <th>Role name</th>
            <th>Description </th>
            <th>status</th>
            <th>action</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Role> roles = (List<Role>) session.getAttribute("roles");
            for (Role role : roles) { %>
        <tr>
            <td><%=role.getId()%>
            </td>
            <td><%=role.getName()%>
            </td>
            <td><%=role.getDescription()%>
            </td>
            <td><%=role.getStatus()%>
            </td>
            <td>
                <a class="btn btn-info"
                   href="<%=uri%>/role?action=view&id=<%=role.getId()%>">View</a>
                <a class="btn btn-primary" href="<%=uri%>/role?action=update&id=<%=role.getId()%>">Update</a>
                <a class="btn btn-danger btn-xoa" onclick="return confirm('Are you sure you want to delete?');"
                   href="<%=uri%>/role?action=delete&id=<%=role.getId()%>">Delete</a>
            </td>
        </tr>
        <%}%>
        </tbody>

    </table>
</div>
</body>
</html>
