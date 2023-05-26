package com.zetcode.Entity;

public class BoxEntity {
    private final int RAND_POS;
    private final int DOT_SIZE;
    private int box_x;
    private int box_y;

    public BoxEntity(int randPos, int dotSize) {
        this.RAND_POS = randPos;
        this.DOT_SIZE = dotSize;
        locateBox();
    }

    public void locateBox() {
        int r = (int) (Math.random() * RAND_POS);
        box_x = (r * DOT_SIZE);

        r = (int) (Math.random() * RAND_POS);
        box_y = (r * DOT_SIZE);
    }


    public int getBoxX() {
        return box_x;
    }

    public int getBoxY() {
        return box_y;
    }
}
