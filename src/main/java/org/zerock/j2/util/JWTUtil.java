package org.zerock.j2.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zerock.j2.controller.interceptor.JWTInterceptor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@Log4j2
public class JWTUtil {

    public static class CustomJWTException extends RuntimeException{
        public CustomJWTException(String msg) {
            super(msg);
        }
    }

    @Value("${org.zerock.jwt.secret}")
    private String key;

    public String generate(Map<String, Object> claimMap, int min){

        // 헤더
        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");

        // claims
        Map<String, Object> claim = new HashMap<>();
        claim.putAll(claimMap);

        SecretKey key = null;

        try {
            key = Keys.hmacShaKeyFor(this.key.getBytes(StandardCharsets.UTF_8));
        }catch (Exception e){

        }
        String jwtStr = Jwts.builder()
                .setHeader(headers)
                .setClaims(claim)
                .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                .setExpiration(
                        Date.from(ZonedDateTime.now().plusMinutes(min).toInstant()))
                .signWith(key)
                .compact();

        return jwtStr;



    }

    public Map<String, Object> validateToken(String token){

        Map<String, Object> claims = null;

        if(token == null){
            throw new CustomJWTException("NullToken");
        }

        try {
            SecretKey key = Keys.hmacShaKeyFor(this.key.getBytes(StandardCharsets.UTF_8));

            claims = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(token).getBody();

        }catch (MalformedJwtException e){
            throw new CustomJWTException("MalFormed");
        }catch (ExpiredJwtException e){
            throw new CustomJWTException("Expired");
        }catch (InvalidClaimException e){
            throw new CustomJWTException("Invalid");
        }catch (JwtException e){
            throw new CustomJWTException(e.getMessage());
        }catch (Exception e){
            throw new CustomJWTException("Error");
        }

        return claims;
    }
}
