package repository.manager;

import org.springframework.data.mongodb.repository.MongoRepository;

import model.booking.Booking;
import model.manager.Manager;

public interface ManagerRepository extends MongoRepository<Manager, String> {
}
