package org.ratelimiter;

import lombok.Getter;

@Getter
public class RateLimiterConfiguration {

    RateLimiter rateLimiter;
    private int capacity = 0;
    private double fillRate = 0;

    public RateLimiterConfiguration() {
        this.rateLimiter = new TokenBucketBasedRateLimiter(capacity, fillRate);
    }

    public RateLimiterConfiguration capacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public RateLimiterConfiguration fillRate(double fillRate) {
        this.fillRate = fillRate;
        return this;
    }

    public RateLimiter configure() {
        return new TokenBucketBasedRateLimiter(capacity, fillRate);
    }
}
