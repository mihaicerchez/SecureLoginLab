package com.company;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main extends JFrame implements ActionListener {

    public static String encryptThisString(String input)
    {
        try {
            //algorithm implementation SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }

            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    private static byte[] Salt() {
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[6];
        random.nextBytes(salt);
        return salt;
    }

    JPanel panel;
    JLabel user_label, password_label, message;
    JTextField userName_text;
    JPasswordField password_text;
    JButton submit, cancel;
    Main() {
        // Username
        user_label = new JLabel();
        user_label.setText("Utilizator: ");
        userName_text = new JTextField();
        // Parola
        password_label = new JLabel();
        password_label.setText("Parola: ");
        password_text = new JPasswordField();
        // Butonul
        submit = new JButton("OK");
        panel = new JPanel(new GridLayout(5, 1));
        panel.add(user_label);
        panel.add(userName_text);
        panel.add(password_label);
        panel.add(password_text);
        message = new JLabel();
        panel.add(message);
        panel.add(submit);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        submit.addActionListener(this);
        add(panel);
        setTitle("Login: ");
        setSize(450,200);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        String userName = userName_text.getText();
        String password = password_text.getText();
        String Salt="[B@eecede2";
        String strHash = null; // Byte to String
        String encrPasss = password+Salt;
        strHash = encryptThisString(encrPasss);

        String encrPass = "4e2ecf3f94df807901672b8de274ba53e3b64bc5e56dbff8baa23f377f022a96ff1405d31ab07d2d9e9f6cb0c37b18fe1a146cf7f802665869cf00e46b72de8c";

        if (userName.trim().equals("Mihai") && strHash.equals(encrPass)) {
            message.setText("Salut, " + userName + ".");
        } else {
            message.setText("Credentiale invalide.");
        }
    }
        public static void main(String[] args) throws
                NoSuchAlgorithmException  {
            new Main();
    }
}