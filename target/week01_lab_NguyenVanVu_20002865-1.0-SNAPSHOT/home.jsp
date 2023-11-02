<%@ page import="vn.edu.iuh.fit.service.AccountService" %>
<%@ page import="vn.edu.iuh.fit.enties.Account" %><%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/11/2023
  Time: 7:10 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home page</title>
    <jsp:include page="./view/template/bootstraplink.jsp"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <link href="static/css/homepage.css"  rel="stylesheet">
</head>
<body>
<%if (session.getAttribute("role").toString().equalsIgnoreCase("admin")) { %>
<jsp:include page="./view/template/nav.jsp"/>
<% }%>
<%
    session.getAttribute("userId").toString();
    AccountService  accountService = new AccountService();
    Account account = accountService.findById(session.getAttribute("userId").toString());

%>
<div class="container-fluid d-flex flex-column justify-content-center align-items-center">
    <div class="d-flex justify-content-center" style="margin-top: 20px;">
        <div class="card" style="width: 350px;">
            <img class="card-img-top"
                 src="https://upload.wikimedia.org/wikipedia/commons/thumb/5/57/OOjs_UI_icon_userAvatar-progressive.svg/1024px-OOjs_UI_icon_userAvatar-progressive.svg.png"
                 alt="Card image" style="width: 100%;">
            <div class="card-body">
                <h4 class="card-title"><%=account.getFullName()%></h4
                <p class="card-text"><%=account.getEmail()%> </p>
                <p class="card-text"><%=account.getPhone()%> </p>
                <p class="card-text"><%=account.getStatus()%> </p>
                <a href="#" class="button d-flex justify-content-center">
                    <i class="fab fa-facebook fa-2x mx-3"></i>
                    <i class="fab fa-instagram fa-2x mx-3"></i>
                    <i class="fab fa-twitter fa-2x mx-3"></i>
                </a>
            </div>
        </div>
    </div>
</div>
</body>

</html>