package com.zetcode.Frame;

import com.zetcode.Snake;
import com.zetcode.SnakeMap2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class MainFrame extends JFrame implements KeyListener{
    public static final int SCREEN_WIDTH=800;
    public static final int SCREEN_HEIGHT=800;
    ImageIcon img=new ImageIcon("src/resources/btn_GoToGame.jpg");
    ImageIcon img2=new ImageIcon("src/resources/btn_GoToGame2.jpg");
    ImageIcon img3=new ImageIcon("src/resources/btn_LevelSelect.jpg");
    ImageIcon img4=new ImageIcon("src/resources/btn_LevelSelect2.jpg");
    ImageIcon img5=new ImageIcon("src/resources/btn_OpenMypage.jpg");
    ImageIcon img6=new ImageIcon("src/resources/btn_OpenMypage2.jpg");
    ImageIcon img7=new ImageIcon("src/resources/btn_CloseMain.jpg");
    ImageIcon img8=new ImageIcon("src/resources/btn_CloseMain2.jpg");
    ImageIcon img9=new ImageIcon("src/resources/btn_GoToMap2.jpg");
    public static final String ID="admin";

    public MainFrame() {
        JButton openMarketButton = new JButton(img3);//
        openMarketButton.setRolloverIcon(img4);
        openMarketButton.setBorderPainted(false);
        openMarketButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMarketFrame();
            }
        });

        JButton openMypageButton = new JButton(img5);
        openMypageButton.setRolloverIcon(img6);
        openMypageButton.setBorderPainted(false);
        openMypageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMypageFrame();
            }
        });

        // 在画板添加地图2的按钮
        JButton openMap2Button = new JButton(img9);
        openMap2Button.setRolloverIcon(img9);
        openMap2Button.setBorderPainted(false);
        openMap2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 按钮点击事件触发画板生成
                openMap2Frame();
            }
        });

        JButton closeMainButton = new JButton(img7);
        closeMainButton.setRolloverIcon(img8);
        closeMainButton.setBorderPainted(false);
        closeMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMainFrame();
            }
        });
        JButton gotoBoardButton = new JButton(img);
        gotoBoardButton.setRolloverIcon(img2);
        gotoBoardButton.setBorderPainted(false);
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
        openMap2Button.setBounds(100, 300, 200, 100);
        openMypageButton.setBounds(100, 400, 200, 100);
        closeMainButton.setBounds(100, 500, 200, 100);

        add(openMarketButton);
        add(openMypageButton);
        add(openMap2Button);
        add(closeMainButton);
        add(gotoBoardButton);
        setTitle("Main");

        ImageIcon background = new ImageIcon("src/resources/main_screen.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(backgroundLabel);

        openMarketButton.addKeyListener(this);
        openMypageButton.addKeyListener(this);
        openMap2Button.addKeyListener(this);
        closeMainButton.addKeyListener(this);
        gotoBoardButton.addKeyListener(this);
        openMarketButton.setFocusable(true);
        openMypageButton.setFocusable(true);
        openMap2Button.setFocusable(true);
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

    private void openMap2Frame() {
        SnakeMap2 snakeMap2 = new SnakeMap2();
        setVisible(false);
        snakeMap2.setVisible(true);
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
