package com.attendance;
import com.attendance.misc.dbConnect;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;

public class EditableTableExample extends JFrame {

    int vClassId;
    int VStudentID;
    String VAtt1 = "";
    String VAtt2 = "";
    Date VAttDate = null;

    ArrayList<Attendance> al = new ArrayList<>();


    public EditableTableExample() {
        String query = "select a.idstudent,a.classid,b.Name,a.Att1,a.Att2 from attendance.Attendance a, attendance.Student b where a.idstudent = b.idstudent;";

        Connection con = dbConnect.getConnection();
//            System.out.println("Suxess");

        try {
            PreparedStatement ps = con.prepareStatement(query);
            System.out.println("1. Prepared Statement");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance at = new Attendance();
                at.setIdStudent(rs.getInt("idstudent"));
                at.setClassId(rs.getInt("classid"));
                at.setName(rs.getString("Name"));

                al.add(at);
                System.out.println("2. Inside While to get data");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AttendanceTableModel model1 = new AttendanceTableModel(al);
        System.out.println("3. Model1 created");
        JFrame f1 = new JFrame();
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(800, 800);

        System.out.println("4. Jtable instance created");
        JTable table = new JTable(model1);

        table.setBounds(10, 10, 800, 800);
        System.out.println("5. Table bounds set");
        f1.add(table);
        table.setVisible(true);

        JButton b = new JButton("Done");
        b.setBounds(320,260,300,100);
        f1.add(b);
        b.setVisible(true);
        table.setBounds(30,60,500,170);

        int totrec = al.size();
        // create the java mysql update preparedstatement
        String queryUpdate = "update attendance.Attendance set Att1 = ?, Att2 = ? where classID = ? and studentID = ? and date = ?";


        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement preparedStmt = con.prepareStatement(queryUpdate);

                    for (int i = 0; i < totrec; i++) {
                        System.out.println(model1.getValueAt(i, 4) + " " + model1.getValueAt(i, 5));

                        if (((Boolean) model1.getValueAt(i, 4)).booleanValue())
                            {
                                preparedStmt.setString(1, "Y");
                            }


                        if (((Boolean) model1.getValueAt(i, 5)).booleanValue())
                            {
                                preparedStmt.setString(2, "Y");

                            }

                    /*vClassId = (int)model1.getValueAt(i, 1);
                    VStudentID = (int)model1.getValueAt(i, 0);*/
//                    VAttDate = ;

//                    preparedStmt.setString(1, VAtt1);
//                    preparedStmt.setString(2, VAtt2);

                            preparedStmt.setInt(3, (int) model1.getValueAt(i, 1));
                            preparedStmt.setInt(4, (int) model1.getValueAt(i, 0));
                            preparedStmt.setDate(5, new java.sql.Date(VAttDate.getTime()));
                            // execute the java preparedstatement
                            preparedStmt.executeUpdate();
                    }
                } catch (Exception e1) {e1.printStackTrace(); }
            }
        });


    }


    public Date StringToDate(String s) {

        Date result = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            result = dateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return result;

    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater
                (new Runnable() {
                     @Override
                     public void run() {
                         new EditableTableExample();
                     }
                 }
                );
    }
}

