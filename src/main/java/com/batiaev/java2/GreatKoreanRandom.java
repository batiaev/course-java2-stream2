package com.batiaev.java2;

import java.util.Random;

public class GreatKoreanRandom implements Obstacle {
    private static final Random random = new Random();

    @Override
    public boolean overcome(Member teamMember) {
        return random.nextBoolean();
    }
}
