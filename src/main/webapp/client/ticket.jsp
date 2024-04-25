<%@ page import="uz.oasis.jsp_cinema_application.repo.SessionRepo" %>
<%@ page import="java.util.UUID" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Session" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Seat" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Ticket" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cinema Ticket Booking</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<%
    String sessionId = request.getParameter("sessionId");
    if (sessionId == null) {
        response.sendRedirect("/");
        return;
    }
    Object object = request.getSession().getAttribute("basketSeats");
    List<Seat> basketSeat;
    if (object == null) {
        basketSeat = new ArrayList<>();
        request.getSession().setAttribute("basketSeats", basketSeat);
    } else {
        basketSeat = (List<Seat>) object;
    }
    SessionRepo sessionRepo = new SessionRepo();
    Session movieSession = sessionRepo.findById(UUID.fromString(sessionId));
    List<Seat> seats = movieSession.getHall().getSeats();
%>

<div class="container mt-5 col-8 offset-2">
    <div class="row">
        <div class="col-6">
            <h2 class="mb-4">Select your seat(s)</h2>
            <form method="post" action="/seat/add">
                <% for (Seat seat : seats) { %>
                <input name="sessionId" value="<%=sessionId%>" type="hidden">
                <button name="seatId" value="<%=seat.getId()%>"
                        class="btn mr-2 mb-2 <%= basketSeat.contains(seat)?"btn-primary" : "btn-outline-secondary"%>"
                        style="width: 90px"
                        <%=seat.isEmpty() ? "" : "disabled"%>><%=seat.getName()%>
                </button>
                <% } %>
            </form>
        </div>
        <div class="col-5 offset-1">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title">Checkout</h2>
                    <ul>
                        <% for (Seat seat : basketSeat) { %>
                        <li><%=seat.getName()%>
                        </li>
                        <% } %>
                    </ul>
                    <p>Total Price: $<span id="total-price"><%=basketSeat.size() * 10%></span></p>
                    <div class="d-flex">
                    <form action="/seat/buy" method="post">
                        <a href="/client/confirmation.jsp" class="btn btn-primary btn-block">Buy</a>
                    </form>
                    <a href="/client/index.jsp" class="btn btn-dark mb-5 ms-4">Back</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
