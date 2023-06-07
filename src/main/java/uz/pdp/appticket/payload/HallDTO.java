package uz.pdp.appticket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HallDTO {
    private String name;
    private Integer capacity;
    private String location;
}
