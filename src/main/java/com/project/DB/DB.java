package com.project.DB;

import java.sql.*;

public class DB {

    public static Connection loadDB() throws SQLException {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
             conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/rentcar_db?serverTimezone=Europe/Moscow&useSSL=false",
                     "root","qw12QW34");
        }catch (Exception ex){
        }
        return conn;
    }
}
