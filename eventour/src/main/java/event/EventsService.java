package event;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import model.event.Event;

//Cambiare il titolo con solo Service
@Service
public class EventsService {
	private static List<Event> eventi = new ArrayList<>();
	
	public EventsService() {
		
	}
	
	public List<Event> getAllEvents(){
		return eventi;
	}
}
