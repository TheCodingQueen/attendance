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


    public EditableTableExample(String pDate, String pClassName, int pClassId ) {
        String query = "select a.idstudent,a.classid,b.Name,a.Att1,a.Att2 from attendance.Attendance a, attendance.Student b where a.idstudent = b.idstudent and a.date = ? and b.ClassId=?";

        VAttDate = StringToDate(pDate);
//        String insQuery = "INSERT INTO Attendance (idStudent,ClassId,date) SELECT idStudent, ClassId,str_to_date(?,'%Y%m%d') FROM Student  where ClassId=?";
        String insQuery = "INSERT INTO Attendance (idStudent,ClassId,date) SELECT idStudent, ClassId, ? FROM Student  where ClassId=?";


        Connection con = dbConnect.getConnection();
            System.out.println("Suxess");

        try {
            System.out.println("First Try block");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setDate(1, new java.sql.Date(VAttDate.getTime()));
            ps.setInt(2,pClassId);

            ResultSet rs = ps.executeQuery();

            if  (!rs.isBeforeFirst())
            {System.out.println("in checking rs for null");
                PreparedStatement ps2 = con.prepareStatement(insQuery);
                ps2.setDate(1, new java.sql.Date(VAttDate.getTime()));
//                ps2.setString(1, pDate);
                ps2.setInt(2,pClassId);
                ps2.executeUpdate(insQuery);
            }


            while (rs.next()) {
                Attendance at = new Attendance();
                at.setidStudent(rs.getInt("idstudent"));
                at.setClassId(rs.getInt("classid"));
                at.setName(rs.getString("Name"));

                al.add(at);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AttendanceTableModel model1 = new AttendanceTableModel(al);
        JFrame f1 = new JFrame(pClassName);
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(800, 800);


        JTable table = new JTable(model1);

        table.setBounds(10, 10, 500, 600);
        f1.add(table);
        table.setVisible(true);
//        table.setBounds(30,60,500,170);

        JButton b = new JButton("Save");
        b.setBounds(550,550,100,50);
        f1.add(b);
        b.setVisible(true);


        int totrec = al.size();
        // create the java mysql update preparedstatement
        String queryUpdate = "update attendance.Attendance set Att1 = ?, Att2 = ? where ClassID = ? and idStudent = ? and date = ?";


        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement preparedStmt = con.prepareStatement(queryUpdate);

                    for (int i = 0; i < totrec; i++) {
                        System.out.println("Student ID >>"+ model1.getValueAt(i, 0) + "   Class Id >> " + model1.getValueAt(i, 1));
                        System.out.println("Date >>" + new java.sql.Date(VAttDate.getTime()));
                        System.out.println(model1.getValueAt(i, 4) + " " + model1.getValueAt(i, 5));

//                        if (((Boolean) model1.getValueAt(i, 4)).booleanValue())
                        if (Boolean.TRUE.equals((model1.getValueAt(i, 4))))
                            {
                                System.out.println("Entered Att1 condition ");
                                preparedStmt.setString(1, "Y");
                            }
                            else { preparedStmt.setString(1, "N");}

//                        if (((Boolean) model1.getValueAt(i, 5)).booleanValue())
                        if (Boolean.TRUE.equals(((model1.getValueAt(i, 5)))))
                            {
                                System.out.println("Entered Att2 condition ");
                                preparedStmt.setString(2, "Y");

                            }
                        else { preparedStmt.setString(2, "N");}
                    vClassId = (int)model1.getValueAt(i, 1);
                    VStudentID = (int)model1.getValueAt(i, 0);

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
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
                         new EditableTableExample("2019-03-15","Test",1);
                     }
                 }
                );
    }
}

