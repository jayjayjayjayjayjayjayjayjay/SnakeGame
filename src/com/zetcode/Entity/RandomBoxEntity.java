package com.zetcode.Entity;

public class RandomBoxEntity {
    private final int RAND_POS;
    private final int DOT_SIZE;
    private int randomBox_x;
    private int randomBox_y;

    public RandomBoxEntity(int randPos, int dotSize) {
        this.RAND_POS = randPos;
        this.DOT_SIZE = dotSize;
        locateRandomBox();
    }

    public void locateRandomBox() {
        int r = (int) (Math.random() * RAND_POS);
        randomBox_x = (r * DOT_SIZE);

        r = (int) (Math.random() * RAND_POS);
        randomBox_y = (r * DOT_SIZE);
    }


    public int getRandomBoxX() {
        return randomBox_x;
    }

    public int getRandomBoxY() {
        return randomBox_y;
    }
}
