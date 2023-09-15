<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LOGIN PAGE</title>
    <jsp:include page="./view/template/bootstraplink.jsp"/>
    <link rel="stylesheet" href="./static/css/login.css">
<%--    <%if (session.getAttribute("status") == null) {%>--%>
<%--    <link rel="stylesheet" href="./static/css/animation.css">--%>
<%--    <%}%>--%>
</head>
<body>
<div class="wrapper fadeInDown">
    <div id="formContent">
        <!-- Tabs Titles -->
        <%if (session.getAttribute("status") != null) {%>
        <div class=" my-4 alert alert-danger alert-dismissible fade show" role="alert">
            <strong><%=session.getAttribute("status")%>
            </strong>
        </div>
        <%}%>

        <h2 class="active"> Sign In </h2>
        <h2 class="inactive underlineHover">Sign Up </h2>
        <!-- Icon -->
        <div class="fadeIn first">
            <i class="fa fa-user-shield"></i>
        </div>
        <form action="<%=request.getContextPath()%>/controllerservlet" method="post">
            <input type="text" id="login" class="fadeIn second" name="username" placeholder="username or email">
            <input type="password" id="password" class="fadeIn third" name="password" placeholder="password">
            <input type="hidden" name="action" value="loginsubmit"/>
            <input type="submit" class="fadeIn fourth" value="Log In">
        </form>
        <div id="formFooter">
            <a class="underlineHover" href="#">Forgot Password?</a>
        </div>
    </div>
</div>
</body>
</html>
