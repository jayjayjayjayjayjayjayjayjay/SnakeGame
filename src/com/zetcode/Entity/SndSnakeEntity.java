package com.zetcode.Entity;


public class SndSnakeEntity {

    private final int[] x2;
    private final int[] y2;
    private int dots2;
    private int hp;

    public SndSnakeEntity(int allDots) {
        x2 = new int[allDots];
        y2 = new int[allDots];
        dots2 = 3;
        hp = 3;

        for (int z = 0; z < dots2; z++) {
            x2[z] = 150 - z * 10;
            y2[z] = 150;
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

    public void move(int dotSize, boolean leftDirection2, boolean rightDirection2, boolean upDirection2, boolean downDirection2) {
        for (int z = dots2; z > 0; z--) {
            x2[z] = x2[z - 1];
            y2[z] = y2[z - 1];
        }

        if (leftDirection2) {
            x2[0] -= dotSize;
        }

        if (rightDirection2) {
            x2[0] += dotSize;
        }

        if (upDirection2) {
            y2[0] -= dotSize;
        }

        if (downDirection2) {
            y2[0] += dotSize;
        }
    }

    public boolean checkCollision(int dotSize, int bWidth, int bHeight) {
        for (int z = dots2; z > 0; z--) {
            if ((z > 4) && (x2[0] == x2[z]) && (y2[0] == y2[z])) {
                return false;
            }
        }


        if (y2[0] >= bHeight || y2[0] < 0 || x2[0] >= bWidth || x2[0] < 0) {
            return false;
        }


        return true;
    }

    public void grow() {
        dots2++;
    }

    public void shrink()  {dots2--;}

    public int[] getX() {
        return x2;
    }

    public int[] getY() {
        return y2;
    }

    public int getDots() {
        return dots2;
    }

}