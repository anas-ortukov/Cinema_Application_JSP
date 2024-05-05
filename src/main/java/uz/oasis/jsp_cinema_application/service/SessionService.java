package uz.oasis.jsp_cinema_application.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
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

public class SessionService {

    static HallRepo hallRepo = new HallRepo();
    static MovieRepo movieRepo = new MovieRepo();
    static SessionRepo sessionRepo = new SessionRepo();
    static SeatRepo seatRepo = new SeatRepo();


    public static void checkTimeAndSave(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String time = req.getParameter("dateTime");
        String hallId = req.getParameter("hallId");
        Hall hall = hallRepo.findById(UUID.fromString(hallId));
        LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        if (!sessionRepo.availableSession(dateTime, hall)) {
            resp.sendRedirect("/admin/addSession.jsp?available=false");
            return;
        }
        saveSession(req, resp,hall, dateTime);
    }

    @SneakyThrows
    private static void saveSession(HttpServletRequest req, HttpServletResponse resp, Hall hall, LocalDateTime dateTime) {
        String movieId = req.getParameter("movieId");
        hall.setSeats(seatRepo.genNewSeats());
        Movie movie = movieRepo.findById(UUID.fromString(movieId));
        Session session = Session.builder()
                .hall(hall)
                .movie(movie)
                .dateTime(dateTime)
                .build();
        sessionRepo.save(session);
        resp.sendRedirect("/admin/sessions.jsp");
    }

    @SneakyThrows
    public static void archiveSession(HttpServletRequest req, HttpServletResponse resp) {
        String id = req.getParameter("id");
        if (id != null) {
            try {
                Session session = sessionRepo.findById(UUID.fromString(id));
                sessionRepo.begin();
                session.setArchived(true);
                sessionRepo.commit();
            }catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/admin/sessions.jsp");
    }
}
