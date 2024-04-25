<%@ page import="uz.oasis.jsp_cinema_application.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 24/04/24
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminNavbar</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    boolean moviePage = request.getRequestURI().equals("/admin/movie.jsp");
    User currentUser = (User) request.getSession().getAttribute("currentUser");
%>
<div class="col-10 offset-1">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark rounded mt-4 shadow">
        <div class="container-fluid py-1">
            <a class="navbar-brand ms-5" href="#">Admin Dashboard</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link <%=!moviePage?"active":""%>" aria-current="page" href="/admin/sessions.jsp">Sessions</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <%=moviePage?"active":""%>" href="/admin/movie.jsp">Movies</a>
                    </li>
                </ul>
                <div class="d-flex">
                    <div class="dropdown me-5">
                        <a class="btn btn-light dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                           data-bs-toggle="dropdown" aria-expanded="false">
                            <%=currentUser.getName()%>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                            <li><a class="dropdown-item" href="/client/myticket.jsp">My Tickets</a></li>
                            <li><a class="dropdown-item" href="/client/index.jsp">Client page</a></li>
                            <li><a class="dropdown-item" href="/auth">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
