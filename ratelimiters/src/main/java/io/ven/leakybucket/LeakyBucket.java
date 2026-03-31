package io.ven.leakybucket;

public class LeakyBucket {
    private int capacity;
    private long lastLeakTimestamp;
    private double leakRatePerMs;
    private int currentWaterLevel;

    public LeakyBucket(int capacity, int leakRatePerSecond) {
        this.capacity = capacity;
        this.leakRatePerMs = leakRatePerSecond / 1000.0;
    }

    public synchronized boolean allowRequest(int amount) {
        leak();
        if (currentWaterLevel + amount <= capacity) {
            currentWaterLevel += amount;
            return true;
        }
        return false;
    }

    private void leak() {
        long now = System.currentTimeMillis();
        long elapsedTime = now - lastLeakTimestamp;
        double leakedAmount = elapsedTime * leakRatePerMs;
        if (leakedAmount > 0) {
            currentWaterLevel = Math.max(0, currentWaterLevel - (int) leakedAmount);
            lastLeakTimestamp = now;
        }
    }
}
