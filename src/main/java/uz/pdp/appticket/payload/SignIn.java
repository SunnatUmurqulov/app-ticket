package uz.pdp.appticket.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignIn {
    private String phoneNumber;
    private String password;
}
