package repository.event;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import functions.TypesE;
import model.event.Event;

public interface EventRepository extends MongoRepository<Event, String> {
	
	@Query("{dataAAAA:'?0'}")
	List<Event> findByYear(Integer Year);
	
	@Query("{dataAAAA:'?0', dataMM:'?1'}")
	List<Event> findByYearMonth(Integer Year, Integer Month);
	
	@Query("{dataAAAA:'?0', dataMM:'?1', dataGG:'?2'}")
	List<Event> findByYearMonthDay(Integer Year, Integer Month, Integer Day);
	
	@Query("{location:'?0'}")
	List<Event> findByLocation(String location);
	
	@Query("{type:'?0'}")
	List<Event> findByType(TypesE type);
	
}
