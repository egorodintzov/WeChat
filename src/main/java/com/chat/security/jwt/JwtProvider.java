package com.chat.security.jwt;

import com.chat.service.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class JwtProvider {

     @Value("$(jwt.secret")
     private String secret;

     @Autowired
     private UserService userService;

     public String generateToken(String login) {
          Map<String,Object> claims = new HashMap<>();
          claims.put("role",userService.findByLogin(login).getRole());
          return Jwts
                     .builder()
                     .setClaims(claims)
                     .setSubject(login)
                     .setIssuedAt(new Date(System.currentTimeMillis()))
                     .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                     .signWith(SignatureAlgorithm.HS256, secret)
                     .compact();
     }

     public String getLoginFromToken(String token) {
          return Jwts
                     .parser()
                     .setSigningKey(secret)
                     .parseClaimsJws(token)
                     .getBody()
                     .getSubject();
     }

     public boolean validityToken(String token,JwtUser jwtUser) {
          return getLoginFromToken(token).equals(jwtUser.getUsername()) &&
                 Jwts
                      .parser()
                      .setSigningKey(secret)
                      .parseClaimsJws(token)
                      .getBody()
                      .getExpiration().after(new Date(System.currentTimeMillis()));
     }

}
