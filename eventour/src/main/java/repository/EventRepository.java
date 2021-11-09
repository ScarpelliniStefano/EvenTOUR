package repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import functions.TypesE;
import model.event.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	List<Event> findByYear(Integer Year);
	List<Event> findByPlace(String place);
	List<Event> findByType(TypesE type);
}
