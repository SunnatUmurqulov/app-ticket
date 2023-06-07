package uz.pdp.appticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appticket.entity.User;

public interface AuthRepository extends JpaRepository<User,Integer> {
    User findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);
}
