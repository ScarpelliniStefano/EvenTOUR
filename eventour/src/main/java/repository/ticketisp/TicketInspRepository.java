package repository.ticketisp;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.booking.Booking;
import model.ticketisp.TicketInsp;

public interface TicketInspRepository extends MongoRepository<TicketInsp, String> {
}
