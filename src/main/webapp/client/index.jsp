<%@ page import="uz.oasis.jsp_cinema_application.repo.MovieRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Objects" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Movie Website</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card:hover {
            transition: box-shadow 0.3s ease;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
        }
    </style>
</head>
<body>

<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    MovieRepo movieRepo = new MovieRepo();
    List<Movie> movies = movieRepo.findAll();
    String basketEmpty = request.getParameter("basketEmpty");
    if (Objects.equals(basketEmpty, "true")) { %>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-danger text-white text-center">
    You didn't choose seat, please try again!
</div>
<% } else if (Objects.equals(basketEmpty, "false")) { %>
<div id="notification" class="notification col-4 offset-4 shadow p-2 rounded mt-4 bg-success text-white text-center">
    Your ticked has been created, you can check it in My Tickets page
</div>
<% } %>


<div class="col-10 offset-1">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark rounded mt-4 shadow">
        <div class="container-fluid py-1">
            <a class="navbar-brand ms-5" href="/">OASIStream</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="/">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/">Movies</a>
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
                            <% if (currentUser.getEmail().equals("dong.lehner@yahoo.com")) { %>
                            <li><a class="dropdown-item" href="/admin/movie.jsp">Admin page</a></li>
                            <% } %>
                            <li><a class="dropdown-item" href="/auth">Logout</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
</div>
<div class="container mt-4">
    <div class="row row-cols-1 row-cols-md-2 row-cols-lg-4 g-4">
        <% for (Movie movie : movies) { %>
        <div class="col">
            <div class="card h-100">
                <img src="/moviePhoto?id=<%=movie.getId()%>" width="300" class="card-img-top img-fluid mx-auto d-block"
                     alt="...">
                <div class="card-body text-center">
                    <h5 class="card-title"><%=movie.getTitle()%>
                    </h5>
                    <span class="badge bg-secondary mb-2"><%= movie.formattedGenres() %></span>
                    <p class="card-text"><%=movie.getDescription()%>
                    </p>
                    <a href="/client/session.jsp?movieId=<%=movie.getId()%>" class="btn btn-dark">Show Session</a>
                </div>
            </div>
        </div>
        <% } %>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        showNotification();
    });

    function showNotification() {
        var notification = document.getElementById("notification");
        notification.style.display = "block";
        setTimeout(function () {
            notification.style.display = "none";
        }, 3000);
    }

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>