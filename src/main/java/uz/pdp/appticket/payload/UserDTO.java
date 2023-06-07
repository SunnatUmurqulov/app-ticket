package uz.pdp.appticket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String password;
    private boolean active = true;
}
