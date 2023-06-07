package uz.pdp.appticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appticket.entity.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
    boolean existsByNameAndHallId(String name, Integer hallId);
}
