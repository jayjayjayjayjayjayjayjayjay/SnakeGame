package com.zetcode.Frame;

import com.zetcode.FirebaseUtil;
import com.zetcode.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ExecutionException;


public class LoginFrame extends JFrame {

    private JTextField idField;
    private JPasswordField pwField;

    public LoginFrame() {
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));//로그인
        JLabel idLabel = new JLabel("ID: ");
        idField = new JTextField(10);
        idPanel.add(idLabel);
        idPanel.add(idField);
        setTitle("Login");

        JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel pwLabel = new JLabel("PW: ");
        pwField = new JPasswordField(10);
        pwPanel.add(pwLabel);
        pwPanel.add(pwField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = String.valueOf(pwField.getPassword());

                try {
                    String validPassword = FirebaseUtil.validateUser(id);
                    if (pw.equals(validPassword)) {
                        openMypageFrame();
                    } else {
                        JOptionPane.showMessageDialog(null, "로그인 실패");
                    }
                } catch (ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterFrame();
            }
        });

        add(idPanel, BorderLayout.NORTH);
        add(pwPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openMypageFrame() {
        MypageFrame mypageFrame = new MypageFrame(new MainFrame());
        setVisible(false);
        mypageFrame.setVisible(true);
    }

    private void openRegisterFrame() {
        RegisterFrame registerFrame = new RegisterFrame(this);
        setVisible(false);
        registerFrame.setVisible(true);
    }



//    public static void main(String[] args) {
//        FirebaseUtil.initialize(); //db 시자크
//
//        LoginFrame loginFrame = new LoginFrame();
//        loginFrame.setVisible(true);
//    }
}
