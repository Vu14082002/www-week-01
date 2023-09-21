<%@ page import="java.util.List" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="vn.edu.iuh.fit.enties.GrantAccess" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/11/2023
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>GRANT-ACCESS</title>
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
<div class="container">
    <div class="row mt-4 mb-3">
        <h1>List GRANT-ACCESS</h1>
    </div>
    <p><a href="<%=request.getContextPath()%>/grant-access?action=add" class="link-underline-primary">Add
        Grant-Access</a></p>
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>Account ID</th>
            <th>Role Id</th>
            <th>Is Grant</th>
            <th>Note</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<GrantAccess> grantAccessList = (List<GrantAccess>) session.getAttribute("grantAccessList");
            for (GrantAccess grantAccess : grantAccessList) {
                String data = grantAccess.getIsGrant() == true ? "ENABLE" : "DIASABLE";
                String id = grantAccess.getAccount().getId() + "-" + grantAccess.getRole().getId();
        %>
        <tr>
            <td><%=grantAccess.getAccount().getId()%>
            </td>
            <td><%=grantAccess.getRole().getId()%>
            </td>
            <td><%=data%>
            </td>
            <td><%=grantAccess.getNote()%>
            </td>
            <td>
                <a class="btn btn-primary"
                   href="<%=uri%>/grant-access?action=update&account=<%=grantAccess.getAccount().getId()%>&role=<%=grantAccess.getRole().getId()%>">Update</a>
                <a class="btn btn-danger btn-xoa" onclick="return confirm('Are you sure you want to delete?');"
                   href="<%=uri%>/grant-access?action=delete&account=<%=grantAccess.getAccount().getId()%>&role=<%=grantAccess.getRole().getId()%>">Delete</a>
            </td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
