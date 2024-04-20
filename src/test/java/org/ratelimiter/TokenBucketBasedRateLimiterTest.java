package org.ratelimiter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TokenBucketBasedRateLimiterTest {

    private RateLimiter rateLimiter;

    @BeforeEach
    void setUp() {
        rateLimiter = new RateLimiterConfiguration()
                .capacity(5)
                .fillRate(0.25)
                .configure();
    }

    @Test
    void testAllowRequest_EnoughTokens() {
        assertTrue(rateLimiter.isRequestAllowed());
        assertTrue(rateLimiter.isRequestAllowed());
    }

    @Test
    void testAllowRequest_CapacityExceeded() {
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.isRequestAllowed());
        }
        assertFalse(rateLimiter.isRequestAllowed());
    }

    @Test
    void testAllowRequest_TokenRefill() {
        for (int i = 0; i < 5; i++) {
            assertTrue(rateLimiter.isRequestAllowed());
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(rateLimiter.isRequestAllowed());
    }

}
