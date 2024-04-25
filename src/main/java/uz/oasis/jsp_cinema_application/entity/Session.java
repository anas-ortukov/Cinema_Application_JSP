package uz.oasis.jsp_cinema_application.entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.oasis.jsp_cinema_application.repo.TicketRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Timestamp
    private LocalDateTime dateTime;
    @ManyToOne
    private Movie movie;
    @ManyToOne
    private Hall hall;



    public Integer getFreeSpace() {
        int i = 0;
        for (Seat seat : hall.getSeats()) {
            if (seat.isEmpty()) {
                i++;
            }
        }
        return i;
    }
}
