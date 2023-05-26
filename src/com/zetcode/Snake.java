package com.zetcode;

import com.zetcode.Frame.MainFrame;
import com.zetcode.Frame.LoginFrame;

import java.awt.EventQueue;
import javax.swing.JFrame;
import com.zetcode.Frame.MainFrame;
public class Snake extends JFrame {
    public Snake() {
        initUI();
        new Sound("sound/bgm.wav");
    }

    private void initUI() {
        
        add(new Board());
               
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    

    public static void main(String[] args) {

        FirebaseUtil.initialize(); //db 시자크
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);

        MainFrame mainFrame1=new MainFrame();
        mainFrame1.setVisible(false);

        EventQueue.invokeLater(() -> {
            JFrame ex = new Snake();
            ex.setVisible(false);
        });
    }
}
