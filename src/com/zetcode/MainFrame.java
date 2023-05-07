package com.zetcode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener{
    public static final int SCREEN_WIDTH=800;
    public static final int SCREEN_HEIGHT=800;
    public MainFrame() {
        JButton openMarketButton = new JButton("Level Select");
        openMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMarketFrame();
            }
        });

        JButton openMypageButton = new JButton("Open Mypage");
        openMypageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMypageFrame();
            }
        });
        JButton closeMainButton = new JButton("Close Main");
        closeMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMainFrame();
            }
        });
        JButton gotoBoardButton = new JButton("Go to game");
        gotoBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBoard();
            }
        });

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        gotoBoardButton.setBounds(100,100,200,100);
        openMarketButton.setBounds(100, 200, 200, 100);
        openMypageButton.setBounds(100, 300, 200, 100);
        closeMainButton.setBounds(100, 400, 200, 100);


        add(openMarketButton);
        add(openMypageButton);
        add(closeMainButton);
        add(gotoBoardButton);
        setTitle("Main");

        ImageIcon background = new ImageIcon("src/resources/main_screen.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(backgroundLabel);

        openMarketButton.addKeyListener(this);
        openMypageButton.addKeyListener(this);
        closeMainButton.addKeyListener(this);
        gotoBoardButton.addKeyListener(this);
        openMarketButton.setFocusable(true);
        openMypageButton.setFocusable(true);
        closeMainButton.setFocusable(true);
        gotoBoardButton.setFocusable(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // ->와<- 방향키로 메뉴버튼 이동
        if (keyCode == KeyEvent.VK_LEFT) {
            this.getFocusOwner().transferFocusBackward();
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            this.getFocusOwner().transferFocus();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    private void openMarketFrame() {
        LevelSelect marketFrame = new LevelSelect(this);

        setVisible(false);

        marketFrame.setVisible(true);
    }

    private void openMypageFrame() {
        MypageFrame mypageFrame = new MypageFrame(this);
        setVisible(false);
        mypageFrame.setVisible(true);
    }

    private void closeMainFrame() {
        System.exit(0);
    }

    private void openBoard() {
        Snake board = new Snake();
        setVisible(false);
        board.setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
