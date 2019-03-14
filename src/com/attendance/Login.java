package com.attendance;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public Login() {
        JFrame f = new JFrame("Log In to Ekya Attendance System");
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().setBackground(Color.pink);

        f.setSize(800, 300);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        JButton b = new JButton("Login");

        JTextField t = new JTextField();
        JTextField t2 = new JTextField();
        JLabel l1 = new JLabel("Email", JLabel.RIGHT);
        JLabel l2 = new JLabel("Password", JLabel.RIGHT);
        l1.setBounds(10, 5, 300, 100);
        l2.setBounds(10, 120, 300, 100);

        t.setBounds(320, 40, 300, 30);
        t2.setBounds(320, 160, 300, 30);

        b.setBounds(320, 230, 150, 30);
        f.add(l1);
        f.add(l2);
        f.add(t);
        f.add(t2);
        f.add(b);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater
                (new Runnable() {
                     @Override
                     public void run() {
                         System.out.println("SEtting bounds");
                         new Login();
                         System.out.println("After");

                     }
                 }
                );
    }


}