
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
        <form action="<%=request.getContextPath()%>/controllerservlet" method="post">
            User name: <input type="text" name="username" > <br>
            Password : <input type="password" name="password"> <br>
            <input type="submit" value="Login">
            <input type="hidden" name="action" value="loginsubmit" >
        </form>
</body>
</html>
