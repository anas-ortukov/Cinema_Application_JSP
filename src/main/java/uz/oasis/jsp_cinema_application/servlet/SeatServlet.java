package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.Seat;
import uz.oasis.jsp_cinema_application.entity.Ticket;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.repo.SeatRepo;
import uz.oasis.jsp_cinema_application.repo.SessionRepo;
import uz.oasis.jsp_cinema_application.repo.TicketRepo;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "Seat servlet", urlPatterns = "/seat/*")
public class SeatServlet extends HttpServlet {

    SeatRepo seatRepo = new SeatRepo();
    TicketRepo ticketRepo = new TicketRepo();

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/seat/add")) {
            String seatId = req.getParameter("seatId");
            String sessionId = req.getParameter("sessionId");
            if (seatId != null) {
                List<Seat> basketSeats = (List<Seat>) req.getSession().getAttribute("basketSeats");
                Seat seat = seatRepo.findById(Integer.parseInt(seatId));
                if (basketSeats.contains(seat)) {
                    basketSeats.remove(seat);
                }else {
                    basketSeats.add(seat);
                }
                req.getSession().setAttribute("basketSeats", basketSeats);
            }
            resp.sendRedirect("/client/ticket.jsp?sessionId="+ sessionId);
        }else if (req.getRequestURI().equals("/seat/buy")) {
            List<Seat> basketSeats = (List<Seat>) req.getSession().getAttribute("basketSeats");
            if (basketSeats.isEmpty()) {
                resp.sendRedirect("/client/index.jsp?basketEmpty=true");
                return;
            }
            User currentUser = (User) req.getSession().getAttribute("currentUser");
            for (Seat basketSeat : basketSeats) {
                Ticket ticket = Ticket.builder()
                        .seat(basketSeat)
                        .user(currentUser)
                        .build();
                seatRepo.begin();
                basketSeat.setEmpty(false);
                seatRepo.commit();
                ticketRepo.save(ticket);
            }
            req.getSession().removeAttribute("basketSeats");
            resp.sendRedirect("/client/index.jsp?basketEmpty=false");
        }
    }
}
