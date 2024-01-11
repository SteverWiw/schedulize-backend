package com.ajsoftware.backendjwtauth.jwt;

import com.ajsoftware.backendjwtauth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
@Slf4j
@Service
public class JwtService {

    @Value("${application.secret.jwt-secret}")
    private String SECRET_KEY;
    public String getToken(User user){
        return getToken(new HashMap<>(),user);
    }

    private String getToken(Map<String,Object> extraClaims, User user) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .claim(JwtClaim.USER_ID.name(), user.getId())
                .claim(JwtClaim.FIRST_NAME.name(), user.getFirstName())
                .claim(JwtClaim.LAST_NAME.name(), user.getLastName())
                .claim(JwtClaim.ROLE_ID.name(), user.getRole().getId())
                .claim(JwtClaim.ROLE_NAME.name(), user.getRole().getDescription())
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + Duration.ofDays(1).toMillis()))
                .signWith(getKey()).compact();
    }
    private SecretKey getKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String getUserNameFromToken(String token) {
        return getClaim(token,Claims::getSubject);

    }
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = getUserNameFromToken(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims(String token){
        try {
            return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
        } catch (Exception e) {
            log.error("Error parsing JWT claims", e);
            throw new RuntimeException("Error parsing JWT claims", e);
        }
    }

    public <T> T getClaim(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = getAllClaims(token);
        return claimsTFunction.apply(claims);

    }

    private Date getExpiration(String token){
        return getClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }

}
