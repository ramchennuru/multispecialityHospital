package com.example.hospital.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDao {

    private BaseDao() {}

    public static Connection getConnection() throws ClassNotFoundException
    {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/multispecialityhospital","root",DBProperties.getPswd());


        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
