<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.Role" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/12/2023
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UPDATE ROLE</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>
<%
    Role role = (Role) request.getAttribute("role");
%>
<jsp:include page="../template/nav.jsp"/>
<div class="containet my-5">
    <form method="post" role="form" class="container" action="<%=request.getContextPath()%>/role">
        <h1>Edit Role</h1>
        <div class="mb-3 ">
            <label class="form-label">Role Name</label>
            <input name="roleName" type="text" class="form-control" value="<%=role.getName()%>">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Description</label>
            <input name="description" type="text" class="form-control" value="<%=role.getDescription()%>">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Status</label>
            <select class="form-select form-select-md mb-3" name="status" aria-label="Large select example">
                <option value="1">ACTIVE</option>
                <option value="0">DEACTIVE</option>
                <option value="-1">DELETE</option>
            </select>
        </div>

        <input type="hidden" name="id" value="<%=role.getId()%>">
        <input type="hidden" name="action" value="update">
        <button type="submit" class="btn btn-primary my-2">Submit</button>
    </form>
</div>
</body>
</html>
