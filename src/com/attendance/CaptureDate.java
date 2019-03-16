package com.attendance;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaptureDate extends JFrame {
    public CaptureDate( int pClassid, String pClassName ) {
        JFrame f1 = new JFrame("Please Provide Date");
        f1.setLayout(null);

        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.getContentPane().setBackground(Color.pink);
        f1.setSize(800, 300);
        f1.setLocationRelativeTo(null);
        f1.setVisible(true);

        JButton b = new JButton("Save");
        JTextField t = new JTextField();
        JLabel l1 = new JLabel("Date", JLabel.RIGHT);

        l1.setBounds(10, 5, 300, 100);
        t.setBounds(320, 40, 300, 30);
        b.setBounds(320, 230, 150, 30);

        f1.add(l1);
        f1.add(t);
        f1.add(b);

        b.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    System.out.println(t.getText());
                                    new EditableTableExample(t.getText(),pClassName,pClassid);
                                }
                            }
        );
    }
        public static void main(String[] args){
            SwingUtilities.invokeLater
                    (new Runnable() {
                         @Override
                         public void run() {
                             System.out.println("SEtting bounds");
                             new CaptureDate(1,"Sammeer");
                             System.out.println("After");

                         }
                     }
                    );


    }

}