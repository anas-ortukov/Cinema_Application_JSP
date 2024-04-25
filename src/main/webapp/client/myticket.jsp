<%@ page import="uz.oasis.jsp_cinema_application.entity.User" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.TicketRepo" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.record.TicketRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="uz.oasis.jsp_cinema_application.entity.Hall" %>
<%@ page import="uz.oasis.jsp_cinema_application.repo.SessionRepo" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Ticket Report</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .ticket {
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 30px;
            display: flex;
            align-items: center;
        }

        .ticket img {
            width: 100px;
            margin-right: 20px;
        }

        .ticket-info {
            flex-grow: 1;
        }

        .ticket p {
            margin: 5px 0;
        }
    </style>
</head>
<body>

<%
    User currentUser = (User) request.getSession().getAttribute("currentUser");
    List<TicketRecord> ticketRecords = TicketRepo.finduserTickets(currentUser);
    SessionRepo sessionRepo = new SessionRepo();
%>

<div class="container mt-5 col-6 offset-3">
    <div class="d-flex w-100 justify-content-between">
        <h1 class="mb-4">My Ticket Report</h1>
        <a href="/client/index.jsp" class="btn btn-dark mb-5 px-3 py-2">Back</a>
    </div>

    <% for (TicketRecord ticketRecord : ticketRecords) { %>
    <div class="ticket shadow">
        <img src="/moviePhoto?id=<%=ticketRecord.movieId()%>" alt="Avengers: Endgame" class="mr-3">
        <div class="ticket-info">
            <h3><%=ticketRecord.movieTitle()%></h3>
            <p>Date-Time: <span style="font-weight: bold"> <%=sessionRepo.showDateTime(ticketRecord.sessionTime().toLocalDateTime())%></span></p>
            <p>Hall: <span style="font-weight: bold"> <%=ticketRecord.hallName()%></span></p>
            <p>Seat Number: <span style="font-weight: bold"> <%=ticketRecord.seatName()%></span></p>
        </div>
    </div>
    <% } %>
</div>
</body>
</html>
