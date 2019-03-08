package com.attendance.misc;

import java.sql.Connection;
import java.sql.DriverManager;

public class dbConnect

{
    public static final String url = "jdbc:mysql://localhost/attendance";

    public static final String user = "root";

    public static final String password = "Mysql@2019";

    public static Connection con;

    public static Connection getConnection() {

        try {
            con = DriverManager.getConnection(url, user, password);

            //return con;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void main(String[] args) {


        try
        {
            Connection connection = dbConnect.getConnection();
            System.out.println("Suxess");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}




