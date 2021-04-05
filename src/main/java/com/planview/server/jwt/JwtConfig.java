package com.planview.server.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application.jwt")
public class JwtConfig {
    private String secretKey;
    private int tokenExpirationDays;

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getTokenExpirationDays() {
        return tokenExpirationDays;
    }

    public void setTokenExpirationDays(int tokenExpirationDays) {
        this.tokenExpirationDays = tokenExpirationDays;
    }

}
