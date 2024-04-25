package uz.oasis.jsp_cinema_application.repo;

import jakarta.persistence.TypedQuery;
import uz.oasis.jsp_cinema_application.entity.Hall;
import uz.oasis.jsp_cinema_application.entity.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


public class SessionRepo extends BaseRepo<Session, UUID> {


    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy 'at' HH:mm");

    public List<Session> findByMovieId(UUID movieId) {
        TypedQuery<Session> query = em.createQuery("from Session s where s.movie.id = :movieId", Session.class);
        query.setParameter("movieId", movieId);
        return query.getResultList();
    }

    public String showDateTime(LocalDateTime dateTime) {
        return dateTime.format(formatter);
    }

    public boolean availableSession(LocalDateTime dateTime, Hall hall) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(dateTime)) {
            System.out.println("it is after now");
            return false;
        }

        for (Session session : findAll()) {
            if (session.getHall().equals(hall)) {
                LocalDateTime twoHours = session.getDateTime().plusHours(2);
                if (dateTime.isAfter(session.getDateTime()) && dateTime.isBefore(twoHours)) {
                    return false;
                }
            }
        }
        return true;
    }
}
