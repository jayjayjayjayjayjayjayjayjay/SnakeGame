package com.zetcode.Frame;

import com.zetcode.Frame.LoginFrame;
import com.zetcode.Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MypageFrame extends JFrame {
    private MainFrame mainFrame;
    private LoginFrame loginFrame;

    public MypageFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Mypage");

        JButton backMainButton = new JButton("Go To Main");
        backMainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMainFrame();
            }
        });
        setLayout(new FlowLayout());
        add(backMainButton);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainFrame.setVisible(true);
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton logoutButton = new JButton("Logout");
        buttonPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                dispose();
            }
        });
        add(buttonPanel,BorderLayout.SOUTH);
    }

    private void backMainFrame() {
        MainFrame mainFrame = new MainFrame();
        setVisible(false);
        mainFrame.setVisible(true);
    }
}
