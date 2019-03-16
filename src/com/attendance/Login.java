package com.attendance;
import com.attendance.misc.dbConnect;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


        String query = "SELECT a.idTeacher,a.TeacherName,a.ClassId,a.ClassName,a.Email,a.Password FROM attendance.ClassTeacher a where a.Email = ? and a.Password= ?";
        Connection con = dbConnect.getConnection();


        b.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    String Vemail;
                                    String Vpwd;
                                    int vClassId=0;
                                    String VClassName= null;

                                    System.out.println(t.getText());

                                    // t = email
                                    //t2 = password

                                    // check against tables

                                try {
                                    PreparedStatement ps = con.prepareStatement(query);
                                    ps.setString(1, t.getText());
                                    ps.setString(2, t2.getText());

                                    ResultSet rs = ps.executeQuery();

                                    while (rs.next()) {
                                        vClassId = rs.getInt(3);
                                        VClassName = rs.getString(4);                                    }
                                }catch (Exception e1) { e1.printStackTrace(); }

                                System.out.println("Class id >> " + vClassId + " Class Name >> " +VClassName);
                                    new CaptureDate(vClassId,VClassName);
                                    }
                                });
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

