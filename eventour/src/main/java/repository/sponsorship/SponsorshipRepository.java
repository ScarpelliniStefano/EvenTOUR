package repository.sponsorship;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.booking.Booking;
import model.sponsorship.Sponsorship;

public interface SponsorshipRepository extends MongoRepository<Sponsorship, String> {
}
