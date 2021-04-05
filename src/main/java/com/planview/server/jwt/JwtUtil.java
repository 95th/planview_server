package com.planview.server.jwt;

import java.security.KeyPair;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

import com.planview.server.entity.User;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);

    private JwtUtil() {}

    public static String createToken(User user) {
        var token = Jwts.builder()
                        .setIssuedAt(new Date())
                        .setSubject(user.getUserName())
                        .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(15).toInstant()))
                        .claim("role", user.getRole())
                        .signWith(keyPair.getPrivate())
                        .compact();

        return token;
    }

    public static Authentication parseToken(String token) {
        var jwt = Jwts.parserBuilder()
                        .setSigningKey(keyPair.getPublic())
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
