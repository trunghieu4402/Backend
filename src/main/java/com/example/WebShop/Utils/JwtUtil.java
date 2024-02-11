package com.example.WebShop.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtUtil {
    public static final String SECRET="413F4428472B4B6250655368566D5970337336763979244226452948404D6351";
    public String generateToken(String username)
    {
        Map<String, Objects> Claim= new HashMap<>();
        return createToken(Claim, username);
    }
    public  String createToken(Map<String, Objects> claim, String username)
    {
        return Jwts.builder()
                .setClaims(claim)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+10000*6*200))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }
    private Key getSignKey()
    {
        byte[] keybyte= Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keybyte);
    }
    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims = extractALlClaim(token);
        return claimsResolver.apply(claims);
    }
    public Claims extractALlClaim(String token)
    {
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }
    private Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }
    public Date extractExpiration(String token)
    {
        return extractClaim(token,Claims::getExpiration);
    }
    public Boolean ValidateToken(String token, UserDetails userDetails)

    {
        final String username = extractUsername(token);
//        if((username.equals(userDetails.getUsername())))
//        {
//            System.out.println("true");
//            System.out.println(userDetails.getAuthorities());
//            System.out.println(userDetails.getPassword());
//        }
        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
    }
}
