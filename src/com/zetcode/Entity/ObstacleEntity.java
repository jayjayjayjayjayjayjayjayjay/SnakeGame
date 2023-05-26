package com.zetcode.Entity;

import java.util.ArrayList;
import java.util.Random;

public class ObstacleEntity {
    private ArrayList<Integer> obstacleX = new ArrayList<>();
    private ArrayList<Integer> obstacleY = new ArrayList<>();
    private int numObstacles;
    private int dotSize;
    private int randPos;

    public ObstacleEntity(int numberOfObstacles, int DOT_SIZE, int RAND_POS) {
        this.numObstacles = numObstacles;
        this.obstacleX = new ArrayList<>();
        this.obstacleY = new ArrayList<>();

        for (int i = 0; i < numberOfObstacles; i++) {
            int r = (int) (Math.random() * RAND_POS);
            obstacleX.add(r * DOT_SIZE);

            r = (int) (Math.random() * RAND_POS);
            obstacleY.add(r * DOT_SIZE);
        }
    }


    public ArrayList<Integer> getObstacleX() {
        return obstacleX;
    }

    public ArrayList<Integer> getObstacleY() {
        return obstacleY;
    }

    public void locateObstacles() {
        for (int i = 0; i < numObstacles; i++) {
            Random random = new Random();
            int randomPos = random.nextInt(randPos);
            obstacleX.add(randomPos * dotSize);
            randomPos = random.nextInt(randPos);
            obstacleY.add(randomPos * dotSize);
        }
    }
}
