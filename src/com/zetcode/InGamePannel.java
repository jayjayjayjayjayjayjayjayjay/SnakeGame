package com.zetcode;
//게임패널클래스 생성
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class InGamePannel extends JPanel implements ActionListener {
    private static final long serialVersionUID = 1L;
    private long startTime;
    private long elapsedTime;
    private Timer timer;
    private Font font;
    private Color fontColor;

    public InGamePannel() {
        startTime = System.currentTimeMillis();
        font = new Font("Arial", Font.BOLD, 12);
        fontColor = Color.YELLOW;
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                elapsedTime = System.currentTimeMillis() - startTime;
                repaint();
            }
        }, 0, 1000);
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 50);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(fontColor);
        g.setFont(font);
        g.drawString(String.format("Time: %02d:%02d:%02d", elapsedTime / 3600000,
                (elapsedTime / 60000) % 60, (elapsedTime / 1000) % 60), 10, 30);
    }

    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}

