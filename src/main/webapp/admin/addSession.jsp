<%@ page import="uz.oasis.jsp_cinema_application.entity.Session" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.MovieRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.HallRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Hall" %>
<%@ page import="java.util.Objects" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Session</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    MovieRepo movieRepo = new MovieRepo();
    HallRepo hallRepo = new HallRepo();
    List<Hall> halls = hallRepo.findAll();
    List<Movie> movies = movieRepo.findAll();
    String available = request.getParameter("available");
    if (Objects.equals(available, "false")) { %>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-danger text-white text-center">
    The chosen time has already passed or has another session
</div>
<% } %>

<div class="container mt-4 col-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4 text-center">Add Session</h2>
            <form action="/session/save" method="post">
                <div class="mb-3">
                    <label for="movie" class="form-label">Movie</label>
                    <select class="form-select" id="movie" name="movieId" required>
                        <option selected disabled>Select Movie</option>
                        <% for (Movie movie : movies) { %>
                        <option value="<%=movie.getId()%>"><%=movie.getTitle()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="hall" class="form-label">Hall</label>
                    <select class="form-select" id="hall" name="hallId" required>
                        <option selected disabled>Select Hall</option>
                        <% for (Hall hall : halls) { %>
                        <option value="<%=hall.getId()%>"><%=hall.getName()%>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="datetime" class="form-label">Date and Time</label>
                    <input type="datetime-local" class="form-control" id="datetime" name="dateTime" required>
                </div>
                <div class="mb-3 d-grid justify-content-center mt-4">
                    <button type="submit" style="width: 100px" class="btn btn-primary">Save</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
