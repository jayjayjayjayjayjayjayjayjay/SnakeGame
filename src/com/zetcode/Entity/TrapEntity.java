package com.zetcode.Entity;

public class TrapEntity {
    private final int RAND_POS;
    private final int DOT_SIZE;
    private int trap_x;
    private int trap_y;

    public TrapEntity(int randPos, int dotSize) {
        this.RAND_POS = randPos;
        this.DOT_SIZE = dotSize;
        locateTrap();
    }

    public void locateTrap() {
        int r = (int) (Math.random() * RAND_POS);
        trap_x = (r * DOT_SIZE);

        r = (int) (Math.random() * RAND_POS);
        trap_y = (r * DOT_SIZE);
    }


    public int getTrapX() {
        return trap_x;
    }

    public int getTrapY() {
        return trap_y;
    }
}
