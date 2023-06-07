package uz.pdp.appticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.Event;
import uz.pdp.appticket.entity.Ticket;
import uz.pdp.appticket.entity.User;
import uz.pdp.appticket.repository.EventRepository;
import uz.pdp.appticket.repository.TicketRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;
    private final EventRepository eventRepository;
    public HttpEntity<ApiResponse> buyTicket(Integer id, User user) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Ticket ticket = new Ticket();
            ticket.setGuestName(user.getFirstName());
            ticket.setEvent(optionalEvent.get());
            ticket.setTime(Timestamp.valueOf(LocalDateTime.now()));
            Event event = optionalEvent.get();
            event.setMaxTicketAmount(event.getMaxTicketAmount() - 1);
            eventRepository.save(event);
            ticketRepository.save(ticket);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse("Successfully ticket created", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Event not found with this: [" + id + "]", false));
    }

    public HttpEntity<?> getTicket(Integer id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            return ResponseEntity.ok().body(ticket);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Ticket not found with this: [" + id + "]", false));
    }

    public HttpEntity<?> deleted(Integer id) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(id);
        if (optionalTicket.isPresent()) {
            ticketRepository.delete(optionalTicket.get());
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ApiResponse("Ticket successfully deleted", true));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Ticket not found with this: [" + id + "]", false));
    }

    public HttpEntity<?> getAllTicket() {
        List<Ticket> all = ticketRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
