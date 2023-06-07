package uz.pdp.appticket.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseToken {
    private String tokenType = "Bearer";
    private String token;

    public ResponseToken(String token) {
        this.token = token;
    }
}
