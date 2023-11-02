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
    <title>Add new account</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>

<jsp:include page="../template/nav.jsp"/>
<div class="containet my-5">
    <form method="post" role="form" class="container my-5" action="<%=request.getContextPath()%>/account">
        <h1>Add Account</h1>
        <div class="mb-3 ">
            <label class="form-label">Account id</label>
            <input name="id" type="text" class="form-control">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Full name</label>
            <input name="fullname" type="text" class="form-control">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Email</label>
            <input name="email" type="email" class="form-control">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Password</label>
            <input name="password" type="password" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input name="phone" type="text" class="form-control">
        </div>
        <div class="mb-3">
            <label class="form-label">Status</label>
            <select class="form-select form-select-md mb-3" name="status" aria-label="Large select example">
                <option value="1">ACTIVE</option>
                <option value="0">DEACTIVE</option>
                <option value="-1">DELETE</option>
            </select>
        </div>

        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-primary my-2">Submit</button>
    </form>
</div>
</body>
</html>
