package com.planview.server.jwt;

import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import com.planview.server.entity.User;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    public JwtUtil(JwtConfig jwtConfig, SecretKey secretKey) {
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    public String createToken(User user) {
        var expiration = ZonedDateTime.now().plusDays(this.jwtConfig.getTokenExpirationDays());
        var token = Jwts.builder()
                        .setIssuedAt(new Date())
                        .setSubject(user.getUserName())
                        .setExpiration(Date.from(expiration.toInstant()))
                        .claim("role", user.getRole())
                        .signWith(secretKey)
                        .compact();

        return token;
    }

    public Authentication parseToken(String token) {
        var jwt = Jwts.parserBuilder()
                      .setSigningKey(secretKey)
                      .build()
                      .parseClaimsJws(token);

        var body = jwt.getBody();
        var user = body.getSubject();
        var role = body.get("role");

        var authority = new SimpleGrantedAuthority("ROLE_" + role);
        var authorities = Collections.singletonList(authority);

        return new UsernamePasswordAuthenticationToken(user, null, authorities);
    }

}
