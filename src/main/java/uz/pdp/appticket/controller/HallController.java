package uz.pdp.appticket.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appticket.entity.Hall;
import uz.pdp.appticket.payload.HallDTO;
import uz.pdp.appticket.service.HallService;

import java.util.List;

@RestController
@RequestMapping("/api/hall")
@RequiredArgsConstructor
@CrossOrigin
public class HallController {
    private final HallService hallService;

    @PreAuthorize(value = "hasAuthority('CREATE_HALL')")
    @PostMapping
    public HttpEntity<?> addHall(@RequestBody HallDTO hallDTO){
        return hallService.addHall(hallDTO);
    }


    @PreAuthorize(value = "hasAuthority('READ_HALL')")
    @GetMapping
    public HttpEntity<?> getAllHall(){
        List<Hall> halls = hallService.getAllHalls();
        return ResponseEntity.ok(halls);
    }


    @PreAuthorize(value = "hasAuthority('READ_HALL')")
    @GetMapping("/{id}")
    public HttpEntity<?> getHallById(@PathVariable Integer id){
        return hallService.getHallById(id);
    }


    @PreAuthorize(value = "hasAuthority('DELETE_HALL')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        return hallService.delete(id);
    }


    @PreAuthorize(value = "hasAuthority('UPDATE_HALL')")
    @PutMapping("/{id}")
    public HttpEntity<?> editHall(@PathVariable Integer id, @RequestBody HallDTO hallDTO){
        return hallService.editHall(id, hallDTO);
    }


}
