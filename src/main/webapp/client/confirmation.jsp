<%@ page import="uz.oasis.jsp_cinema_application.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ticket Purchase Confirmation</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    String manyTimes = request.getParameter("multiple");
    if (manyTimes != null) { %>
<div id="notification" class="notification col-4 offset-4 mt-4 p-2 bg-danger text-white text-center">
    Code is wrong. The new code has been sent! Please, check your email again!
</div>
<% } %>

<div class="container py-5">
    <div class="row">
        <div class="col-md-6 mx-auto text-center">
            <div class="card shadow-lg p-4">
                <h1 class="mb-4">Ticket Purchase Confirmation</h1>
                <p class="mb-4">A confirmation code has been sent to your email. Please check your inbox.</p>
                <p class="mb-4">The code was sent to: <strong><%=currentUser.getEmail()%></strong></p>
                <form action="/seat/buy" method="post">
                    <div class="form-group">
                        <label for="confirmationCode">Enter Confirmation Code:</label>
                        <input type="text" id="confirmationCode" name="code" class="form-control" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Confirm Purchase</button>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- Bootstrap JS and jQuery (optional) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>