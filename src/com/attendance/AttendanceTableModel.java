package com.attendance;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import com.attendance.Attendance;

public class AttendanceTableModel extends AbstractTableModel
{
    private final List<Attendance> attendanceList;
    
    private final String[] columnNames = new String[]
            {
            "StudentId", "ClassId", "Name","AttDate","Att1", "Att2"
    };
    private final Class[] columnClass = new Class[]
            {
        Integer.class, Integer.class,String.class, Date.class,Boolean.class,Boolean.class
    };

    public AttendanceTableModel(List<Attendance> pAttendanceList)
    {
        this.attendanceList = pAttendanceList;
    }
    
    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }

    @Override
    public int getColumnCount()
    {
        return columnNames.length;
    }

    @Override
    public int getRowCount()
    {
        return attendanceList.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Attendance row = attendanceList.get(rowIndex);
        if(0 == columnIndex)
        {
            return row.getIdStudent();
        }
        else if(1 == columnIndex)
        {
            return row.getClassId();
        }
        else if(2 == columnIndex)
        {
            return row.getName();
        }
        else if(3 == columnIndex)
        {
            return row.getAttDate();
        }
        else if(4 == columnIndex)
        {
            return row.getAtt1();
        }
        else if( 5== columnIndex)
        {
            return row.getAtt2();
        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Attendance row = attendanceList.get(rowIndex);
        if(0 == columnIndex)
        {
            row.setIdStudent((Integer) aValue);
        }
        else if(1 == columnIndex)
        {
            row.setName((String) aValue);
        }
        else if(2 == columnIndex)
        {
            row.setClassId((Integer) aValue);
        }
        else if(3 == columnIndex)
        {
            row.setAtt1((boolean) aValue);
        }
        else if(4 == columnIndex)
        {
            row.setAtt2((boolean) aValue);
        }
    }


}
