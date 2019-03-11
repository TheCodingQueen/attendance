package com.attendance;
import com.attendance.misc.dbConnect;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditableTableExample extends JFrame
{
    public EditableTableExample()
    {


        ArrayList<Attendance> al = new ArrayList<>();
        String query = "select a.idstudent,a.classid,b.Name,a.Att1,a.Att2 from attendance.Attendance a, attendance.Student b where a.idstudent = b.idstudent;";

        Connection con = dbConnect.getConnection();
//            System.out.println("Suxess");

    try {
        PreparedStatement ps = con.prepareStatement(query);

        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            Attendance at = new Attendance();
            at.setIdStudent(rs.getInt("idstudent"));
            at.setClassId(rs.getInt("classid"));
            at.setName(rs.getString("Name"));

            al.add(at);

        }
        } catch (Exception  e) {  e.printStackTrace();  }

    AttendanceTableModel model1 = new AttendanceTableModel(al);

        JFrame f1= new JFrame();
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(800,800);

        JTable table = new JTable(model1);


        JButton b= new JButton();
        b.setBounds(10,230,300,100);

        b.addActionListener(new ActionListener()
        {
            public void actionPerformed( ActionEvent e)

            {
                int totrec = al.size();

                for(int i =0;i<totrec; i++) {
                    System.out.println(model1.getValueAt(i,3) );
                }

            }

        });
        table.setBounds(10, 10 , 800 , 800);


        f1.add(b);
        f1.add(table);
        b.setVisible(true);
        table.setVisible(true);
    /*&
        add a button

        add event listener for button press
        when pressed:
            iterate through table
            read att1 + 2
            update array list (al)

         */
    }
    
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater
                (new Runnable()
                 {
            @Override
            public void run()
            {
                new EditableTableExample();
            }
        }
        );
    }    
}
