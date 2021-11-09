package repository.booking;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.booking.Booking;

public interface BookingRepository extends MongoRepository<Booking, String> {
}
