package com.planview.server.repos;

import java.security.KeyPair;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Date;

import com.planview.server.entity.LoginDetails;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Repository
public class JwtRepo {
    private static final KeyPair keyPair = Keys.keyPairFor(SignatureAlgorithm.RS256);
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public JwtRepo(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String getToken(LoginDetails loginDetails) {
        var user = this.userRepo.findByUserName(loginDetails.getUsername());
        if (!this.passwordEncoder.matches(loginDetails.getPassword(), user.getPassword())) {
            return null;
        }

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
