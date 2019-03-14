package com.attendance;

import java.util.Date;

public class Attendance
{
    private int idStudent;
    private int ClassId;
    private String Name;
    private Date AttDate;
    private Boolean Att1;
    private Boolean  Att2;

    public Attendance(int idStudent, int ClassId, String name, Boolean Att1, Boolean Att2, Date AttDate)
    {


        this.idStudent = idStudent;
        this.ClassId=ClassId;
        this.Name = name;
        this.AttDate =AttDate;
        this.Att1=Att1;
        this.Att2=Att2;

    }

    public Attendance(int idStudent, int ClassId, String name, Boolean Att1, Boolean Att2)
    {


        this.idStudent = idStudent;
        this.ClassId=ClassId;
        this.Name = name;
/*        this.AttDate =AttDate;*/
        this.Att1=Att1;
        this.Att2=Att2;

    }

    public Attendance(){};

    public int getIdStudent()
    {
        return idStudent;
    }

    public void setIdStudent(int IdStudent)
    {
        this.idStudent = IdStudent;
    }
    public int getClassId()
    {
       return ClassId;
    }


    public void setClassId(int ClassId)
    {
        this.ClassId = ClassId;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        this.Name = name;
    }

    public  void setAtt1(boolean Att1)
    {
            this.Att1 = Att1;
    }

    public Boolean getAtt1()
    {
        return Att1;

    }
    public  void setAtt2(boolean Att2)
    {
        this.Att2 = Att2;
    }

    public Boolean getAtt2()
    {
        return Att2;

    }
    public void setAttDate()
    {
        this.AttDate= AttDate;
    }
    public Date getAttDate()
    {
        return AttDate;

    }
}
