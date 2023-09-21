<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.GrantAccess" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/12/2023
  Time: 9:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>
<%
    GrantAccess grantAccess = (GrantAccess) session.getAttribute("grantAccess");
%>
<jsp:include page="../template/nav.jsp"/>
<div class="containet my-5">
    <form method="post" role="form" class="container" action="<%=request.getContextPath()%>/grant-access">
        <h1>Edit Grant Access</h1>
        <div class="mb-3 ">
            <label class="form-label">Account Id</label>
            <input name="accountCheckerId" type="text" class="form-control" value="<%=grantAccess.getAccount().getId()%>" readonly>
        </div>
        <div class="mb-3 ">
            <label class="form-label">Role Id</label>
            <input name="roleChecker" type="text" class="form-control" value="<%=grantAccess.getRole().getId()%>" readonly>
        </div>
        <div class="mb-3 ">
            <label class="form-label">Grant</label>
            <select class="form-select form-select-md mb-3" name="isGrant" aria-label="Large select example">
                <option value="ENABLE">ENABLE</option>
                <option value="DISABLE">DISABLE</option>
            </select>
        </div>
        <div class="form-floating">
            <textarea class="form-control" placeholder="Leave a note here" name="note" style="height: 100px" ><%=grantAccess.getNote()%></textarea>
            <label>Note</label>
        </div>
        <input type="hidden" name="accountId" value="<%=grantAccess.getAccount().getId()%>">
        <input type="hidden" name="roleId" value="<%=grantAccess.getRole().getId()%>">
        <input type="hidden" name="action" value="update">
        <button type="submit" class="btn btn-primary my-2">Submit</button>
    </form>
</div>
</body>
</html>
