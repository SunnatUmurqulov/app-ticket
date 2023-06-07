package uz.pdp.appticket.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.Event;
import uz.pdp.appticket.entity.Hall;
import uz.pdp.appticket.exception.MyCustomException;
import uz.pdp.appticket.payload.EventDTO;
import uz.pdp.appticket.repository.EventRepository;
import uz.pdp.appticket.repository.HallRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final HallRepository hallRepository;

    public HttpEntity<?> addEvent(EventDTO eventDTO) {
        boolean exists = eventRepository.existsByNameAndHallId(eventDTO.getName(),eventDTO.getHallId());
        if (exists) {
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new ApiResponse("Event this by exists",false));
        }
        Optional<Hall> optionalHall = hallRepository.findById(eventDTO.getHallId());
        if (optionalHall.isPresent()) {
            Event event = new Event();
            event.setHall(optionalHall.get());
            event.setName(eventDTO.getName());
            event.setDescription(eventDTO.getDescription());
            event.setTime(eventDTO.getTime());
            event.setMaxTicketAmount(eventDTO.getMaxTicketAmount());
            eventRepository.save(event);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Successfully added", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Hall not found", false));
    }

    public HttpEntity<?> getAllEvent() {
        List<Event> all = eventRepository.findAll();
        return ResponseEntity.ok().body(all);
    }

    public HttpEntity<?> getOneEventById(Integer id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            return ResponseEntity.status(HttpStatus.OK).body(event);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Event is not found", false));
    }

    public HttpEntity<?> deleteEvent(Integer id) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("Event is not found", false));
        }
        Event event = optionalEvent.get();
        event.setIsDeleted(true);
        eventRepository.save(event);
        return ResponseEntity.ok().body(new ApiResponse("Successfully deleted", true));
    }

    public HttpEntity<?> editEvent(Integer id, EventDTO eventDTO) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event event = optionalEvent.get();
            Event eventMapping = eventMapping(event, eventDTO);
            if (eventMapping == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ApiResponse("Event not found with this:[" + eventDTO.getHallId() + "] id", false));
            }
            return ResponseEntity.ok()
                    .body(new ApiResponse("Successfully edited", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Event not found [" + id + "] id", false));
    }

    private Event eventMapping(Event event, EventDTO eventDTO) {
        Optional<Hall> hall = hallRepository.findById(eventDTO.getHallId());
        if (hall.isPresent()) {
            event.setHall(hall.get());
            event.setName(eventDTO.getName());
            event.setDescription(eventDTO.getDescription());
            event.setTime(eventDTO.getTime());
            event.setMaxTicketAmount(eventDTO.getMaxTicketAmount());
            return event;
        }
        return null;
    }
}
