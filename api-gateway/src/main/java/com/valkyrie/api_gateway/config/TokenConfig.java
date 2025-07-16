package com.valkyrie.api_gateway.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class TokenConfig {
    @Value("${jwts.security}")
    private String securityKey;

    private Key generateKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(securityKey));
    }

    private <Instance> Instance getClaims(String token, Function<Claims, Instance> claimsBearer) {
        Claims claims = Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
        return claimsBearer.apply(claims);
    }

    private boolean isExpired(String token) {
        return !getClaims(token, Claims::getExpiration).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims).setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(generateKey()).compact();
    }

    public String getUsername(String token) {
        return getClaims(token, Claims::getSubject);
    }

    public boolean isValid(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(getUsername(token)) && isExpired(token);
    }
}
