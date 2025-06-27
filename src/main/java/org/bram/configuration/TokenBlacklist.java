package org.bram.configuration;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class TokenBlacklist {

    private final Set<String> blackistedTokens = ConcurrentHashMap.newKeySet();

    public void getBlackistedTokens(String token) {
        blackistedTokens.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blackistedTokens.contains(token);
    }
}
