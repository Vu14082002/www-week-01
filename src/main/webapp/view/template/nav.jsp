<%--
  Created by IntelliJ IDEA.
  User: December
  Date: 09/12/2023
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%
    String uri = request.getContextPath();
%>
<nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">DASHBOARDS SYSTEM</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=uri%>/controllerservlet?action=account">Account
                        Manager</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=uri%>/controllerservlet?action=role">Role
                        Manager</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=uri%>/controllerservlet?action=log">Log
                        Manager</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<%=uri%>/controllerservlet?action=grant-accesses">Grant
                        Access Manager</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page"
                       href="<%=uri%>/controllerservlet?action=logout">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
