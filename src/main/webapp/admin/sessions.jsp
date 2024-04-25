<%@ page import="uz.oasis.jsp_cinema_application.repo.MovieRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.SessionRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Session" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 24/04/24
  Time: 19:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session Admin Page</title>
</head>
<body>

<%@ include file="adminNavbar.jsp"%>
<%
    SessionRepo sessionRepo = new SessionRepo();
    List<Session> sessions = sessionRepo.findAll();
%>

<div class="container mt-5">

    <div class="d-flex w-100 justify-content-between mb-4">
        <h1>Sessions</h1>
        <a href="/admin/addSession.jsp" class="btn btn-primary mb-3 me-2">Add Session</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Movie</th>
            <th scope="col">DateTime</th>
            <th scope="col">Hall</th>
            <th scope="col">Available seats</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Session movieSession : sessions) { %>
        <tr>
            <td><%=movieSession.getMovie().getTitle()%></td>
            <td> <%=sessionRepo.showDateTime(movieSession.getDateTime())%></td>
            <td><%=movieSession.getHall().getName()%></td>
            <td><%=movieSession.getFreeSpace()%></td>
            <td>
                <a href="/admin/editSession.jsp?id=<%=movieSession.getId()%>" class="btn btn-secondary">Edit</a>
                <a href="#" class="btn btn-danger">Delete</a>
            </td>
        </tr>
        <%   } %>
        </tbody>
    </table>
</div>
</body>
</html>
