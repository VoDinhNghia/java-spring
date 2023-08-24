package spring.springboot.utils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtToken {
    private final static String secretKey = "springBootJavaKeyTeststsjdkdkkd";
    private final static int expireTime = 24 * 60 * 60 * 1000;

    private static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secretKey), SignatureAlgorithm.HS512.getJcaName());
    public String generateJwtToken(String email) {
        String token = Jwts.builder().setSubject(email).setExpiration(new Date(System.currentTimeMillis() + expireTime)).signWith(SignatureAlgorithm.HS512, hmacKey).compact();
        return token;
    }

    public String getEmailFromToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token);
        return claims.getBody().getSubject();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(hmacKey).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token invalid");
        }
    }

    public String handleAndGetEmailFromToken(String tokenBearer) {
        String token = tokenBearer.replace("Bearer ", "").trim();
        String email = getEmailFromToken(token);
        return email;
    }

    // public String[] decoBase64(String token) {
    //     byte[] decoBasedBytes = Base64.getDecoder().decode(token);
    //     String pairedCredentials = new String(decoBasedBytes);
    //     String[] credentials = pairedCredentials.split(":", 2);
    //     return credentials;
    // }
}
