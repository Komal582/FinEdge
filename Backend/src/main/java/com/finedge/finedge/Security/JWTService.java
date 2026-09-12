package com.finedge.finedge.Security;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    
    
    private static final SecretKey key = Keys.hmacShaKeyFor("njhefjkasdkjqjwkjowjekjoroweoqjowjd".getBytes());
   

    public String generateToken(UserDetails userDetails){
       return Jwts.builder()
            .subject(userDetails.getUsername())
            .issuedAt(new Date())
            .expiration(new Date(new Date().getTime() + (1000 * 60 * 60 * 24)))
            .signWith(key)
            .compact();
    }


    private Claims extractAllClaims(String token) {
    return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
       }

    public String extractUsername(String token){
        return extractAllClaims(token).getSubject();
    }

    private Date extraExpiration(String token){
        return extractAllClaims(token).getExpiration();
    }

    private boolean isTokenExpired(String token){
        return extraExpiration(token).before(new Date());

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        if(extractUsername(token).equals(userDetails.getUsername()) && !isTokenExpired(token)){
            return true ;
        }
        else{
            return false;
        }
    }

}
