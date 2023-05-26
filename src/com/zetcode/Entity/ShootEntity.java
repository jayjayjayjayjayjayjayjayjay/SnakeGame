package com.zetcode.Entity;

import java.util.ArrayList;

public class ShootEntity {
    private ArrayList<Integer> shootX = new ArrayList<>();
    private ArrayList<Integer> shootY = new ArrayList<>();
    private ArrayList<Integer> directionX = new ArrayList<>();
    private ArrayList<Integer> directionY = new ArrayList<>();

    public ShootEntity() {
    }

    public void addShoot(int x, int y, int dirX, int dirY) {
        shootX.add(x);
        shootY.add(y);
        directionX.add(dirX);
        directionY.add(dirY);
    }

    public void updatePositions(int speed) {
        for (int i = 0; i < shootX.size(); i++) {
            shootX.set(i, shootX.get(i) + directionX.get(i) * speed);
            shootY.set(i, shootY.get(i) + directionY.get(i) * speed);
        }
    }

    public ArrayList<Integer> getShootX() {
        return shootX;
    }

    public ArrayList<Integer> getShootY() {
        return shootY;
    }

}
