package uz.oasis.jsp_cinema_application.entity.record;

import uz.oasis.jsp_cinema_application.entity.Movie;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

public record TicketRecord(UUID movieId, String movieTitle, Timestamp sessionTime, String hallName, String seatName) {
}
