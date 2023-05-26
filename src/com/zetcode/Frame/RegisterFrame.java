package com.zetcode.Frame;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.WriteResult;
import com.zetcode.FirebaseUtil;
import com.zetcode.Frame.LoginFrame;
import com.zetcode.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class RegisterFrame extends JFrame {

    private LoginFrame loginFrame;
    private JTextField idField;
    private JPasswordField pwField;

    public RegisterFrame(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;

        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel idLabel = new JLabel("ID: ");
        idField = new JTextField(10);
        idPanel.add(idLabel);
        idPanel.add(idField);

        JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel pwLabel = new JLabel("PW: ");
        pwField = new JPasswordField(10);
        pwPanel.add(pwLabel);
        pwPanel.add(pwField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("Register");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);

        // In RegisterFrame class
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String pw = String.valueOf(pwField.getPassword());

                try {
                    FirebaseUtil.addUser(new User(id, pw));
                    JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.");
                    loginFrame.setVisible(true);
                    dispose();
                } catch (ExecutionException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.setVisible(true);
                dispose();
            }
        });

        add(idPanel, BorderLayout.NORTH);
        add(pwPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
