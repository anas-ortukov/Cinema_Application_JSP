<%@ page import="uz.oasis.jsp_cinema_application.repo.SessionRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Session" %>
<%@ page import="java.time.temporal.TemporalField" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.MovieRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Movie" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Details</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%
    SessionRepo sessionRepo = new SessionRepo();
    String id = request.getParameter("movieId");
    if (id == null) {
        response.sendRedirect("/");
        return;
    }
    MovieRepo movieRepo = new MovieRepo();
    Movie movie = movieRepo.findById(UUID.fromString(id));
    List<Session> sessions = sessionRepo.findByMovieId(movie.getId());
    %>
<%--<div class="container mt-4">--%>
<%--    <div class="row justify-content-center">--%>
<%--        <div class="col-md-8">--%>
<%--            <h2 class="mb-4">Movie Details</h2>--%>
<%--            <div class="card">--%>
<%--                <div class="card-body">--%>
<%--                    <h5 class="card-title">Movie Title</h5>--%>
<%--                    <p class="card-text">Description of the movie.</p>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            <h3 class="mt-4">Sessions</h3>--%>
<%--            <table class="table">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>Time</th>--%>
<%--                    <th>Places left</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>
<%--                <% for (Session movieSession : sessions) { %>--%>
<%--                <tr>--%>
<%--                    <td><%= movieSession.getDateTime() %></td>--%>
<%--                    <td><%= 15 %></td>--%>
<%--                </tr>--%>
<%--                <% } %>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--    </div>--%>
<%--</div>--%>
<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <h2 class="mb-4 text-center">Movie Details</h2>
            <div class="card shadow" style="height: 300px">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="/moviePhoto?id=<%=movie.getId()%>" class="img-fluid rounded-start" alt="Movie Image" style="height: 298px">
                    </div>
                    <div class="col-md-8 align-content-center">
                        <div class="card-body">
                            <h3 class="card-title"><%=movie.getTitle()%></h3>
                            <p class="card-text"><%=movie.getDescription()%></p>
                        </div>
                    </div>
                </div>
            </div>
            <h3 class="mt-5 text-center">Sessions</h3>
            <div class="list-group mt-3">
                <% for (Session movieSession : sessions) { %>
                <a href="/client/ticket.jsp?sessionId=<%=movieSession.getId()%>" class="btn btn-secondary p-3 mt-4 shadow">
                    <div class="d-flex w-100 justify-content-between">
                        <p class="mb-1" style="font-size: 18px"><%=movieSession.getHall().getName()%></p>
                        <p class="mb-1" style="font-size: 18px"><%= sessionRepo.showDateTime(movieSession.getDateTime()) %></p>
                        <small class="align-content-center">Places Left: <span style="font-weight: bold"> <%= movieSession.getFreeSpace()%></span></small>
                    </div>
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
