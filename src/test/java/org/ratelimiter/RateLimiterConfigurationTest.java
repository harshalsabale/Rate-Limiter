package org.ratelimiter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RateLimiterConfigurationTest {

    @Test
    void rateLimiterConfiguration_defualt() {
        RateLimiterConfiguration limiter = new RateLimiterConfiguration();

        assertEquals(0, limiter.getCapacity());
        assertEquals(0, limiter.getFillRate());
    }

    @Test
    void rateLimiterConfiguration_modified() {
        RateLimiterConfiguration limiter = new RateLimiterConfiguration()
                .capacity(5)
                .fillRate(0.25);

        assertEquals(5, limiter.getCapacity());
        assertEquals(0.25, limiter.getFillRate());
    }
}
