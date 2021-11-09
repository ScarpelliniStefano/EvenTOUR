package repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.booking.Booking;
import model.user.User;

public interface UserRepository extends MongoRepository<User, String> {
}
