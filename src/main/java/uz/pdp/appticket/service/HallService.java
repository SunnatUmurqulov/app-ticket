package uz.pdp.appticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.appticket.entity.ApiResponse;
import uz.pdp.appticket.entity.Hall;
import uz.pdp.appticket.exception.MyCustomException;
import uz.pdp.appticket.payload.HallDTO;
import uz.pdp.appticket.repository.HallRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class HallService {
    private final HallRepository hallRepository;

    public HttpEntity<?> addHall(HallDTO hallDTO) {
        boolean exists = hallRepository.existsByName(hallDTO.getName());
        if (exists){
            return ResponseEntity.status(HttpStatus.FOUND)
                    .body(new ApiResponse("Hall by is exists",false));
        }
        Hall hall = Hall.builder()
                .capacity(hallDTO.getCapacity())
                .name(hallDTO.getName())
                .location(hallDTO.getLocation())
                .build();
        Hall save = hallRepository.save(hall);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse("Successfully added", true));
    }

    public List<Hall> getAllHalls() {
        return hallRepository.findAll();
    }

    public HttpEntity<?> getHallById(Integer id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isPresent()) {
            Hall hall = optionalHall.get();
            return ResponseEntity.ok().body(hall);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse("Hall is not found", false));
    }

    public HttpEntity<?> delete(Integer id) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Hall is not found", false));
        }
        Hall hall = optionalHall.get();
        hall.setRemoved(true);
        hallRepository.save(hall);
        return ResponseEntity.status(OK)
                .body(new ApiResponse("Successfully deleted",true));
    }

    public HttpEntity<?> editHall(Integer id, HallDTO hallDTO) {
        Optional<Hall> optionalHall = hallRepository.findById(id);
        if (optionalHall.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse("Hall is not found", false));
        }

        Hall hall = optionalHall.get();
        hall.setName(hallDTO.getName());
        hall.setLocation(hallDTO.getLocation());
        hall.setCapacity(hallDTO.getCapacity());
        hallRepository.save(hall);
        return ResponseEntity.status(OK)
                .body(new ApiResponse("Successfully edited",true));
    }
}
