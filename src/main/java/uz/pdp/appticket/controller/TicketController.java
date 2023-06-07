package uz.pdp.appticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.User;
import uz.pdp.appticket.payload.TicketDTO;
import uz.pdp.appticket.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
@CrossOrigin
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/buy/{id}")
    public HttpEntity<ApiResponse> buyTicket(@PathVariable Integer id,@AuthenticationPrincipal User user){
        return ticketService.buyTicket(id, user);
    }

    @GetMapping
    public HttpEntity<?> getAllTicket(){
        return ticketService.getAllTicket();
    }


    @GetMapping("/{id}")
    public HttpEntity<?> getTicket(@PathVariable Integer id){
        return ticketService.getTicket(id);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_TICKET')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleted(@PathVariable Integer id){
        return ticketService.deleted(id);
    }
}
