package uz.pdp.appticket.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.pdp.appticket.entity.User;
import uz.pdp.appticket.repository.AuthRepository;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${jwt.token.expireTime}")
    private Long expireTime;
    @Value("${jwt.token.secretKey}")
    private String secretKey;
    private final AuthRepository authRepository;

    public  String generateJwtToken(Integer userId) {
        Date date = new Date();
        String token = Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(date)
                .setExpiration(new Date(date.getTime() + expireTime))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
        return token;
    }


    public  boolean validateToken(String token) {
        try {
            Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            System.err.println("Muddati o'tgan");
        } catch (MalformedJwtException malformedJwtException) {
            System.err.println("Buzilgan token");
        } catch (SignatureException s) {
            System.err.println("Kalit so'z xato");
        } catch (UnsupportedJwtException unsupportedJwtException) {
            System.err.println("Qo'llanilmagan token");
        } catch (IllegalArgumentException ex) {
            System.err.println("Bo'sh token");
        }
        return false;
    }

    public User getUserFromToken(String token) {
        User user = null;
        if (validateToken(token)) {
            String phoneNumber = Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
            user = authRepository.findById(Integer.parseInt(phoneNumber)).orElse(null);
        }
        return user;
    }
}
