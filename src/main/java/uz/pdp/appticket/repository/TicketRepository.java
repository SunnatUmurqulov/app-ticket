package uz.pdp.appticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appticket.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
