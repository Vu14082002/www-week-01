<%@ page import="vn.edu.iuh.fit.enties.Account" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/12/2023
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ADD NEW ROLE</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>

<jsp:include page="../template/nav.jsp"/>
<div class="container ">
    <form method="post" role="form" class="container my-5 " action="<%=request.getContextPath()%>/role">
        <h1>Add Role</h1>
        <div class="mb-3 ">
            <label class="form-label">Role id</label>
            <input name="id" type="text" class="form-control">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Role name</label>
            <input name="roleName" type="text" class="form-control">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Description</label>
            <input name="description" type="text" class="form-control">
        </div>
        <select class="form-select form-select-md mb-3" name="status" aria-label="Large select example">
            <option value="1">ACTIVE</option>
            <option value="0">DEACTIVE</option>
            <option value="-1">DELETE</option>
        </select>
        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-primary my-2">Submit</button>
    </form>
</div>
</body>
</html>
