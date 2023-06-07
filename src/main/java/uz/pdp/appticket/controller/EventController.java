package uz.pdp.appticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appticket.payload.EventDTO;
import uz.pdp.appticket.repository.EventRepository;
import uz.pdp.appticket.service.EventService;

@RestController
@RequestMapping("/api/event")
@RequiredArgsConstructor
@CrossOrigin
public class EventController {

    private final EventService eventService;

    @PreAuthorize(value = "hasAuthority('CREATE_EVENT')")
    @PostMapping
    public HttpEntity<?> addEvent(@RequestBody EventDTO eventDTO){
        return eventService.addEvent(eventDTO);
    }

    @PreAuthorize(value = "hasAuthority('READ_EVENT')")
    @GetMapping
    public HttpEntity<?> getAllEvent(){
        return eventService.getAllEvent();
    }

    @PreAuthorize(value = "hasAuthority('READ_EVENT')")
    @GetMapping("/{id}")
    public HttpEntity<?> getOneEvent(@PathVariable Integer id){
        return eventService.getOneEventById(id);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_EVENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deletedEvent(@PathVariable Integer id){
        return eventService.deleteEvent(id);
    }

    @PreAuthorize(value = "hasAuthority('UPDATE_EVENT')")
    @PutMapping("/{id}")
    public HttpEntity<?> updateEvent(@PathVariable Integer id, @RequestBody EventDTO eventDTO){
        return eventService.editEvent(id,eventDTO);
    }
}
