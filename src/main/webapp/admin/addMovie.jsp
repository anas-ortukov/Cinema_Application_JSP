<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Movie</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    Object moviePhoto = request.getSession().getAttribute("moviePhoto");
%>

<div class="container mt-4 col-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h2 class="mb-4 text-center">Add Movie</h2>
            <form action="/moviePhoto" method="post" enctype="multipart/form-data">
                <div class="mb-3">
                    <label for="photo" class="form-label">
                        <input class="d-none" type="file" id="photo" name="moviePhoto" accept="image/*" >
                        <% if (moviePhoto == null) {%>
                        <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSWzAhSWnwOj9_d_o8BDFth60wVR0OO5wzxqevZbQ9WyA&s" width="100px" id="photo">
                        <%} else { %>
                        <img src="/moviePhoto" width="100px" id="photo">
                        <% } %>
                    </label>
                    <button class="btn btn-outline-primary mx-5" type="submit">Upload</button>
                </div>
            </form>
            <form action="/movie/save" method="post">
                <div class="mb-3">
                    <label for="title" class="form-label">Title</label>
                    <input type="text" class="form-control" id="title" name="title" required>
                </div>
                <div class="mb-3">
                    <label for="genre" class="form-label">Genre</label>
                    <input type="text" class="form-control" id="genre" name="genre" required>
                </div>
                <div class="mb-3">
                    <label for="description" class="form-label">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="2" required></textarea>
                </div>
                <div class="mb-3 d-grid justify-content-center" >
                    <button type="submit" style="width: 120px" class="btn btn-primary">Add Movie</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
