package com.example.ecommerce.ecommerce.Security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;


import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JWTUtil {
    @Value("${jwt.secret_key}")
    private String SECRET_kEY;
    @Value("${jwt.time_expiration}")
    private Integer TIME_EXPIRED;

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey("SECRET_kEY").parseClaimsJwt(token).getBody().getSubject();
    }
    public Date extractExpired(String token){
        return Jwts.parser().setSigningKey("SECRET_kEY").parseClaimsJwt(token).getBody().getExpiration();
    }
    public List<GrantedAuthority> getRole(String token){
        Claims claims = Jwts.parser()
                .setSigningKey("SECRET_kEY")
                .parseClaimsJws(token)
                .getBody();
        List<GrantedAuthority> authorities = Arrays.stream(claims.get("roles").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return authorities;
    }

    public Boolean isExpired(String token){
        return extractExpired(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String user = extractUsername(token);
        return userDetails.getUsername().equals(user) && !isExpired(token);
    }
    public String generateToken(UserDetails userDetails){
        return Jwts.builder().setSubject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRED))

                .signWith(SignatureAlgorithm.HS256, SECRET_kEY).compact();
    }
}
