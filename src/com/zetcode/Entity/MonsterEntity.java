package com.zetcode.Entity;

import java.util.ArrayList;
import java.util.Random;

public class MonsterEntity {
    private ArrayList<Integer> monsterX = new ArrayList<>();
    private ArrayList<Integer> monsterY = new ArrayList<>();
    private ArrayList<Integer> monsterDirectionX = new ArrayList<>();
    private ArrayList<Integer> monsterDirectionY = new ArrayList<>();
    private int numMonsters;
    private int dotSize;
    private int randPos;
    private long lastShootTime;

    private ShootEntity shootEntity;
    private int shootInterval = 5000; // 몬스터 총알 발사 주기


    public MonsterEntity(int numberOfMonsters, int DOT_SIZE, int RAND_POS) {
        this.numMonsters = numberOfMonsters;
        this.dotSize = DOT_SIZE;
        this.randPos = RAND_POS;

        for (int i = 0; i < numberOfMonsters; i++) {
            int r = (int) (Math.random() * RAND_POS);
            monsterX.add(r * DOT_SIZE);

            r = (int) (Math.random() * RAND_POS);
            monsterY.add(r * DOT_SIZE);

            monsterDirectionX.add(0);
            monsterDirectionY.add(0);
        }
        this.shootEntity = new ShootEntity();
        lastShootTime = System.currentTimeMillis();
    }


    public ArrayList<Integer> getMonsterX() {
        return monsterX;
    }

    public ArrayList<Integer> getMonsterY() {
        return monsterY;
    }

    public void updateMonsterAndShootPositions(SnakeEntity snake, int shootSpeed) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastShootTime >= shootInterval) {
            for (int i = 0; i < numMonsters; i++) {
                int directionX = (int) Math.signum(snake.getX()[0] - monsterX.get(i));
                int directionY = (int) Math.signum(snake.getY()[0] - monsterY.get(i));

                // Choose X or Y direction randomly
                if (new Random().nextBoolean()) {
                    shootEntity.addShoot(monsterX.get(i), monsterY.get(i), directionX, 0);
                } else {
                    shootEntity.addShoot(monsterX.get(i), monsterY.get(i), 0, directionY);
                }
            }
            lastShootTime = currentTime;
        }

        shootEntity.updatePositions(shootSpeed);
    }
    public ShootEntity getShootEntity() {
        return shootEntity;
    }


}
