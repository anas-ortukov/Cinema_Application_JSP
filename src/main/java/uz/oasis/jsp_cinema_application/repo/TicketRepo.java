package uz.oasis.jsp_cinema_application.repo;

import jakarta.persistence.Query;
import uz.oasis.jsp_cinema_application.entity.Ticket;
import uz.oasis.jsp_cinema_application.entity.User;
import uz.oasis.jsp_cinema_application.entity.record.TicketRecord;

import java.util.List;
import java.util.UUID;

public class TicketRepo extends BaseRepo<Ticket, UUID> {

    @SuppressWarnings("unchecked")
    public static List<TicketRecord> findUserTickets(User currentUser) {
        String query = """
                            SELECT m.id, m.title, ses.datetime, h.name, s.name FROM Ticket t
                            JOIN seat s ON s.id = t.seat_id
                            JOIN hall_seat hs on s.id = hs.seats_id
                            JOIN hall h on hs.hall_id = h.id
                            JOIN sessions ses on h.id = ses.hall_id
                            JOIN public.movie m on ses.movie_id = m.id
                            WHERE t.user_id = :userId""";
        Query nativeQuery = em.createNativeQuery(query, TicketRecord.class);
        nativeQuery.setParameter("userId", currentUser.getId());
        return (List<TicketRecord>) nativeQuery.getResultList();

    }
}
