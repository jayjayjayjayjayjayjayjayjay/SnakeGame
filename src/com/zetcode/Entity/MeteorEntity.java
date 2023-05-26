package com.zetcode.Entity;

import java.awt.Image;
import javax.swing.ImageIcon;

public class MeteorEntity {
    private int[] meteor_x;
    private int meteor_y = 0;
    private Image meteorImage;
    private int meteorNum;

    public MeteorEntity(int meteorNum) {
        this.meteorNum = meteorNum;
        meteor_x = new int[meteorNum];
        for (int i = 0; i < meteorNum; i++) {
            meteor_x[i] = (i + 1) * (800 / (meteorNum + 1));
        }
        loadImage();
    }

    private void loadImage() {
        ImageIcon meteorIcon = new ImageIcon("src/resources/meteor.png");
        meteorImage = meteorIcon.getImage();
    }

    public void updatePosition(int deltaY) {
        meteor_y += deltaY; // 메테오가 아래로 떨어지도록 더하기로 변경
    }

    public int[] getMeteorX() {
        return meteor_x;
    }

    public int getMeteorY() {
        return meteor_y;
    }

    public Image getMeteorImage() {
        return meteorImage;
    }
}
