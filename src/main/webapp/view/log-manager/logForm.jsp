<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.Log" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/12/2023
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOG UPDATE</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>
<%
    Log log = (Log) request.getAttribute("log");
%>
<jsp:include page="../template/nav.jsp"/>
<div class="containet my-5">
    <form method="post" role="form" class="container" action="<%=request.getContextPath()%>/log">
        <h1>Edit Log</h1>
        <div class="mb-3 ">
            <label class="form-label">Account Id</label>
            <input readonly name="accountId" type="email" class="form-control" value="<%=log.getAccountId()%>">
        </div>
        <div class="mb-3 ">
            <label class="form-label">Login Time</label>
            <input readonly name="login" type="text" class="form-control" value="<%=log.getLoginTime()%>">
        </div>
        <div class="mb-3">
            <label class="form-label">Logout Time</label>
            <input readonly name="logout" type="text" class="form-control" value="<%=log.getLogoutTime()%>">
        </div>
        <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a note here" name="note" style="height: 100px"><%=log.getNote()%></textarea>
            <label>Note</label>
        </div>
        <input type="hidden" name="id" value="<%=log.getId()%>">
        <input type="hidden" name="action" value="update">
        <button type="submit" class="btn btn-primary my-2">Submit</button>
    </form>
</div>
</body>
</html>
