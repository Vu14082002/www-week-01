<%@ page import="vn.edu.iuh.fit.enties.Account" %>
<%@ page import="java.util.List" %>
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
    <title>Add new account</title>
    <jsp:include page="../template/bootstraplink.jsp"/>
</head>
<body>

<jsp:include page="../template/nav.jsp"/>

<%
    List<Role> roleList = (List<Role>) session.getAttribute("roleList");
    List<Account> accountList = (List<Account>) session.getAttribute("accountList");
%>
<div class="containet my-5">
    <form method="post" role="form" class="container" action="<%=request.getContextPath()%>/grant-access">
        <h1>Add Grant-Access</h1>

        <div class="mb-3 ">
            <label class="form-label">Account id</label>
            <select class="form-select form-select-md mb-3"  name="accountId" aria-label="Large select example">
                <%
                    for (Account account : accountList) {
                %>
                <option value=<%=account.getId()%>><%=account.getId()%>
                </option>
                <% }%>
            </select>
        </div>
        <%
         for (Role role: roleList){ %>
        <div class="form-check">
            <input class="form-check-input" type="checkbox"  name="roleId" value="<%=role.getId()%>" >
            <label class="form-check-label" >
                <%=role.getId()%>
            </label>
        </div>
        <% }%>
        <div class="mb-3 ">
            <label class="form-label">GRANRT ACCESS</label>
            <input name="isGrant" type="text" class="form-control" value="ENABLE" readonly>
        </div>
        <div class="form-floating my-3">
            <textarea class="form-control" placeholder="Leave a note here" name="note" style="height: 100px"></textarea>
            <label>Note</label>
        </div>
        <input type="hidden" name="action" value="add">
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
</body>
</html>
