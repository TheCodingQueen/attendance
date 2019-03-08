package com.attendance;
import com.attendance.misc.dbConnect;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EditableTableExample extends JFrame
{
    public EditableTableExample()
    {
        //build the list
//        List<Attendance> AttendanceList = new ArrayList<Attendance>();
/*        Attendance row1 = new Attendance(1, 1, "John", true, false);
          Attendance row2 = new Attendance(2, 1, "Rambo", true, false);
          Attendance row3 = new Attendance(3, 1, "Zorro", true, true);
*/
//        public static ArrayList<Attendance> getAssignedUserPrivileges(User u) throws Exception {
        ArrayList<Attendance> al = new ArrayList<>();
        String query = "select a.idstudent,a.classid,b.Name,a.Att1,a.Att2 from attendance.Attendance a, attendance.Student b where a.idstudent = b.idstudent;";

        Connection con = dbConnect.getConnection();
//            System.out.println("Suxess");

    try {
        PreparedStatement ps = con.prepareStatement(query);
//        ps.setInt(1, u.getUserId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Attendance at = new Attendance();
            at.setIdStudent(rs.getInt("idstudent"));
            at.setClassId(rs.getInt("classid"));
            at.setName(rs.getString("Name"));
//            at.setAtt1(rs.Boolean("Att1"));
//            at.setAtt2(rs.getString("Att2"));
            al.add(at);

        }
    } catch (Exception  e) {
        e.printStackTrace();

    }

//        return al;

        
        //create the model
        AttendanceTableModel model = new AttendanceTableModel(al);
        //create the table
        JTable table = new JTable(model);
//        AttendanceList.add(row1);
//        AttendanceList.add(row2);
//        AttendanceList.add(row3);

        //add the table to the frame

        this.add(new JScrollPane(table));
        
        this.setTitle("Editable Table Example");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        
        this.pack();
        this.setVisible(true);
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
