package org.ratelimiter;

import java.time.Duration;
import java.time.Instant;

public class TokenBucketBasedRateLimiter implements RateLimiter {

    private final int capacity;
    private final double fillRate;
    private int tokens;
    private Instant lastRefillTime;

    public TokenBucketBasedRateLimiter(int capacity, double fillRate) {
        this.capacity = capacity;
        this.fillRate = fillRate;
        this.tokens = capacity;
        this.lastRefillTime = Instant.now();
    }

    public synchronized boolean isRequestAllowed() {
        refillBucket();
        if(tokens <= 0) {
            return false;
        }
        tokens--;
        return true;
    }

    private void refillBucket() {
        Instant currentTime = Instant.now();
        Duration difference = Duration.between(lastRefillTime, currentTime);
        lastRefillTime = currentTime;
        int generatedTokens = (int) Math.floor(difference.toSeconds() * fillRate);
        tokens = Math.min(capacity, tokens + generatedTokens);
    }
}
