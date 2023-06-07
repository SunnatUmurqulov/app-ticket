package uz.pdp.appticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appticket.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Integer> {
    boolean existsByName(String name);
}
