package io.ven.game;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    private final int diceCount;
    private static final int MIN = 1;
    private static final int MAX = 6;

    public Dice(int diceCount) {
        this.diceCount = diceCount;
    }

    public int rollDice() {
        int diceUsed = 0;
        int rollScore = 0;
        while(diceUsed < diceCount) {
            rollScore += ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
            diceUsed++;
        }
        return rollScore;
    }
}
