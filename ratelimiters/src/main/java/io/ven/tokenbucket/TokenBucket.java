package io.ven.tokenbucket;

public class TokenBucket {
    private final int capacity;
    private final int refillRate; // tokens to be refilled per second
    private double availableTokens;
    private long lastRefillTimestamp;

    public TokenBucket(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.availableTokens = capacity;
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest(int tokensNeeded) {
        refill();
        if (availableTokens >= tokensNeeded) {
            availableTokens -= tokensNeeded;
            return true;
        }
        return false;
    }

    private void refill() {
        long currentTimeStamp = System.currentTimeMillis();
        double elaspedTime = (currentTimeStamp - lastRefillTimestamp) / 1000.0;
        double tokensToAdd = elaspedTime * refillRate;
        availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
        lastRefillTimestamp = currentTimeStamp;
    }
}
