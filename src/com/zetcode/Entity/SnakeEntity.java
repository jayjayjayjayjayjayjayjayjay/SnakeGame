package com.zetcode.Entity;


public class SnakeEntity {

    private final int[] x;
    private final int[] y;
    private int dots;
    private int hp;

    public SnakeEntity(int allDots) {
        x = new int[allDots];
        y = new int[allDots];
        dots = 3;
        hp = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
    }
    // hp getter
    public int getHp() {
        return hp;
    }

    // hp 감소 메서드
    public void decreaseHp() {
        hp--;
    }

    public void move(int dotSize, boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
        for (int z = dots; z > 0; z--) {
            x[z] = x[z - 1];
            y[z] = y[z - 1];
        }

        if (leftDirection) {
            x[0] -= dotSize;
        }

        if (rightDirection) {
            x[0] += dotSize;
        }

        if (upDirection) {
            y[0] -= dotSize;
        }

        if (downDirection) {
            y[0] += dotSize;
        }
    }

    public boolean checkCollision(int dotSize, int bWidth, int bHeight) {
        for (int z = dots; z > 0; z--) {
            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                return false;
            }
        }


        if (y[0] >= bHeight || y[0] < 0 || x[0] >= bWidth || x[0] < 0) {
            return false;
        }


        return true;
    }

    public void grow() {
        dots++;
    }
    public void shrink() { dots--; }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getDots() {
        return dots;
    }

}
