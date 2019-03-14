package com.attendance;
import com.attendance.misc.dbConnect;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public EditableTableExample() {
        int VclassID;
        int VStudentID;
        String VAtt1 = "";
        String VAtt2 = "";
        Date VAttDate = null;

        ArrayList<Attendance> al = new ArrayList<>();
        String query = "select a.idstudent,a.classid,b.Name,a.Att1,a.Att2 from attendance.Attendance a, attendance.Student b where a.idstudent = b.idstudent;";

        Connection con = dbConnect.getConnection();
//            System.out.println("Suxess");

        try {
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Attendance at = new Attendance();
                at.setIdStudent(rs.getInt("idstudent"));
                at.setClassId(rs.getInt("classid"));
                at.setName(rs.getString("Name"));

                al.add(at);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        AttendanceTableModel model1 = new AttendanceTableModel(al);

        JFrame f1 = new JFrame();
        f1.setVisible(true);
        f1.setLayout(null);
        f1.setSize(800, 800);

        JTable table = new JTable(model1);


        JButton b = new JButton();
        b.setBounds(10, 330, 300, 100);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int totrec = al.size();
                // create the java mysql update preparedstatement
//                String query = "update attendance.Attendance set Att1 = ?, Att2 = ? where classID = ? and studentID = ? and date = ?";
//                PreparedStatement preparedStmt = conn.prepareStatement(query);

                for (int i = 0; i < totrec; i++)
                {
                    System.out.println(model1.getValueAt(i, 4) + " " + model1.getValueAt(i, 5));

                    if (model1.getValueAt(i, 1) = true) {
                        VAtt1 = "Y";
                }


                    if (model1.getValueAt(i, 5) = true) {
                        VAtt2 = "Y";
                    }

                    VclassID = model1.getValueAt(i, 1);
                    VStudentID = model1.getValueAt(i, 0);
//                    VAttDate = ;

                    ps.setString(1, VAtt1);
                    ps.setString(2, VAtt2);
                    ps.setInt(3, VclassID);
                    ps.setInt(4, VStudentID);
                    ps.setInt(5, VAttDate);

                    /*  need two String variables VAtt1, VAtt2 to hold value for Att1 & Att2
                        get value of model1.getValueAt(i,3) & model1.getValueAt(i,4)
                        get value of classID model1.getValueAt(i,0)  into a variable VclassId
                        get value of studentID model1.getValueAt(i,1)  into a variable VstudentID

                       if model1.getValueAt(i,3) = TRUE then
                       {VAtt1="y";}

                       if model1.getValueAt(i,4) = TRUE then
                       {VAtt2="y";}

                       VclassID = model1.getValueAt(i,1);
                       VStudent =model1.getValueAt(i,0);
                       VAttDate = This is date for which Attendance is being captured and this must be captured in UI from the user into a variable;
                        then set value of VAtt1 ='Y' VAtt2='Y'

                        preparedStmt.setString(1, VAtt1);
                        preparedStmt.setString(2, VAtt2);
                        preparedStmt.setInt   (3, VclassID);
                        preparedStmt.setInt   (4, vstudentID);
                        preparedStmt.setInt   (5, VAttDate);
                      // execute the java preparedstatement
                      preparedStmt.executeUpdate();
                    */
                }
            }
        });

        table.setBounds(10, 10, 800, 800);


        f1.add(b);
        f1.add(table);
        b.setVisible(true);
        table.setVisible(true);
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

