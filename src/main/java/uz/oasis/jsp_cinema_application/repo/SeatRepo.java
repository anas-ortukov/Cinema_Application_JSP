package uz.oasis.jsp_cinema_application.repo;

import uz.oasis.jsp_cinema_application.entity.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatRepo extends BaseRepo<Seat, Integer> {
    public List<Seat> genNewSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            Seat seat = Seat.builder()
                    .name("Seat " + (i + 1))
                    .isEmpty(true)
                    .build();
            save(seat);
            seats.add(seat);
        }
        return seats;
    }
}
