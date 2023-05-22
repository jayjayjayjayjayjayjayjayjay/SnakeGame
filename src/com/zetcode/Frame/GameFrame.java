package com.zetcode.Frame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Font;
import com.zetcode.Board;

public class GameFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel gamePanel;


    public GameFrame() {
        initUI();
    }

    private void initUI() {
        setTitle("Game Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        InGamePannel gamePanel = new InGamePannel();
        JPanel btnPanel = new JPanel();

        JButton endGameBtn = new JButton("End Game");
        endGameBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(GameFrame.this,
                        "Are you sure you want to end the game?", "End Game",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // 关闭所有窗口
                    Window[] windows = Window.getWindows();
                    for (Window window : windows) {
                        window.dispose();
                    }
                    new MainFrame().setVisible(true);
                }
            }
        });

        btnPanel.add(endGameBtn);

        add(gamePanel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.PAGE_END);
        pack();
        setLocationRelativeTo(null);

    }
    private void showGameOverPanel(){
        JPanel gameOverPanel = new JPanel();
        gameOverPanel.setPreferredSize(new Dimension(800, 800));
        JLabel gameOverLabel = new JLabel("Game Over");
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 36));
        gameOverPanel.add(gameOverLabel);
        add(gameOverPanel, BorderLayout.CENTER);
        validate();
    }
    private void stopGame(){
        ((Board) gamePanel).stopTimer();
        showGameOverPanel();
    }
    private void continueGame(){
        ((Board) gamePanel).continueTimer();
        showGameOverPanel();
    }

    public static void main(String[] args) {
    }
}