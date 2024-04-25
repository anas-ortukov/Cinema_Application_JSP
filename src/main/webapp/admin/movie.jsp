<%@ page import="uz.oasis.jsp_cinema_application.repo.MovieRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Movie" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: anas
  Date: 24/04/24
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movie Admin page</title>
    <style>
        tbody td {
            vertical-align: middle;
            horiz-align: center;
        }
    </style>
</head>
<body>

<%@ include file="adminNavbar.jsp" %>
<%
    MovieRepo movieRepo = new MovieRepo();
    List<Movie> movies = movieRepo.findAll();
%>

<div class="container mt-5">

    <div class="d-flex w-100 justify-content-between mb-4">
        <h1>Movies</h1>
        <a href="/admin/addMovie.jsp" class="btn btn-primary mb-3 me-2">Add Movie</a>
    </div>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Image</th>
            <th scope="col">Description</th>
            <th scope="col">Genre</th>
            <th scope="col">Sessions</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <% for (Movie movie : movies) { %>
        <tr>
            <td><%=movie.getTitle()%>
            </td>
                <td><img src="/moviePhoto?id=<%=movie.getId()%>" height="100px"></td>
            <td style="max-width: 200px; overflow: auto;"><textarea rows="4" cols="25"> <%=movie.getDescription()%></textarea>
            </td>
            <td><%=movie.getGenre()%>
            </td>
            <td style="width: 120px"><%=movieRepo.numMovieSessions(movie)%>
            </td>
            <td>
                <a href="/admin/editMovie.jsp?id=<%=movie.getId()%>" class="btn btn-secondary me-2">Edit</a>
                <a href="#" class="btn btn-danger">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
</body>
</html>
