package uz.oasis.jsp_cinema_application.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import uz.oasis.jsp_cinema_application.entity.Hall;
import uz.oasis.jsp_cinema_application.entity.Movie;
import uz.oasis.jsp_cinema_application.entity.Session;
import uz.oasis.jsp_cinema_application.repo.HallRepo;
import uz.oasis.jsp_cinema_application.repo.MovieRepo;
import uz.oasis.jsp_cinema_application.repo.SeatRepo;
import uz.oasis.jsp_cinema_application.repo.SessionRepo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@WebServlet(name = "Session Servlet", urlPatterns = "/session/*")
public class SessionServlet extends HttpServlet {

    HallRepo hallRepo = new HallRepo();
    MovieRepo movieRepo = new MovieRepo();
    SessionRepo sessionRepo = new SessionRepo();
    SeatRepo seatRepo = new SeatRepo();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getRequestURI().equals("/session/save")) {
            String time = req.getParameter("dateTime");
            String hallId = req.getParameter("hallId");
            Hall hall = hallRepo.findById(UUID.fromString(hallId));
            LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (!sessionRepo.availableSession(dateTime, hall)) {
                resp.sendRedirect("/admin/addSession.jsp?available=false");
                return;
            }
            String movieId = req.getParameter("movieId");
            hall.setSeats(seatRepo.genNewSeats());
            Movie movie = movieRepo.findById(UUID.fromString(movieId));
            Session session = Session.builder()
                    .hall(hall)
                    .movie(movie)
                    .dateTime(dateTime)
                    .build();
            sessionRepo.save(session);
        }
    }
}
