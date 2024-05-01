package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.Seat;
import uz.oasis.jsp_cinema_application.entity.Ticket;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.repo.SeatRepo;
import uz.oasis.jsp_cinema_application.repo.TicketRepo;

import java.io.IOException;
import java.util.List;

public class SeatService {

    static SeatRepo seatRepo = new SeatRepo();
    static TicketRepo ticketRepo = new TicketRepo();

    @SuppressWarnings("unchecked")
    public static void confirmation(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Seat> basketSeats = (List<Seat>) req.getSession().getAttribute("basketSeats");
        Object object = req.getSession().getAttribute("code");
        String code = req.getParameter("code");
        if (!object.toString().equals(code)) {
            resp.sendRedirect("/client/confirmation.jsp?multiple=true");
            return;
        }
        if (basketSeats.isEmpty()) {
            resp.sendRedirect("/client/index.jsp?basketEmpty=true");
            return;
        }
        makeOrder(req, resp, basketSeats);
    }

    private static void makeOrder(HttpServletRequest req, HttpServletResponse resp, List<Seat> basketSeats) throws IOException {
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

    @SuppressWarnings("unchecked")
    public static void setSeatToSession(HttpServletRequest req, HttpServletResponse resp) throws IOException {
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
    }

}
