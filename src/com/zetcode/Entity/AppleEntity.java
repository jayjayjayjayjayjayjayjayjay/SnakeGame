package com.zetcode.Entity;

public class AppleEntity {
    private final int RAND_POS;
    private final int DOT_SIZE;
    private int apple_x;
    private int apple_y;

    public AppleEntity(int randPos, int dotSize) {
        this.RAND_POS = randPos;
        this.DOT_SIZE = dotSize;
        locateApple();
    }

    public void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = (r * DOT_SIZE);

        r = (int) (Math.random() * RAND_POS);
        apple_y = (r * DOT_SIZE);
    }


    public int getAppleX() {
        return apple_x;
    }

    public int getAppleY() {
        return apple_y;
    }
}
