package jfdev.apitodolist.infra;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jfdev.apitodolist.model.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Component
public class TokenService {

    @Value("${api.secret.token}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create().withIssuer("api-ag")
                    .withClaim("login", user.getEmail()).withClaim("nome", user.getName())
                    .withClaim("id", user.getId())
                    .withClaim("email", user.getEmail()).withSubject(user.getEmail()).sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new RuntimeException("Não foi possível gerar o token");
        }
    }

    public String validateToken(String token) {

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).withIssuer("api-ag").build().verify(token).getSubject();

        } catch (JWTVerificationException ex) {
            return "";
        }
    }

    private Instant getExpirationDate() {
        return LocalDateTime.now().plusHours(5).toInstant(ZoneOffset.of("-03:00"));
    }
}