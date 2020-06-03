package com.project.DB;

import com.project.entities.User;
import com.project.enums.UserStatus;

import java.sql.*;
import java.util.ArrayList;

public class UserDB {

    private static final String url="jdbc:mysql://localhost:3306/rentcar_db?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String root="root";
    private static final String password="qw12QW34";


    /**
     * Shows all users from database
     * @return List of users
     */
    public static ArrayList<User> getUsers()  {

        ArrayList<User> users=new ArrayList<User>();
        String sql="SELECT * FROM users";
            try{
                Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
                try(Connection conn=DriverManager.getConnection(url,root,password)) {
                    Statement statement=conn.createStatement();
                    ResultSet resultSet=statement.executeQuery(sql);
                    while (resultSet.next()){
                        User user= getUser(resultSet);
                        users.add(user);
                    }
                }
            } catch (Exception ex){

            }

        return users;
    }

    /**
     * Finds user from database by id
     * @param id id of user
     * @return user
     */
    public static User getUserById(int id) {
        User user=null;
        String sql="SELECT * FROM users WHERE id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        user= getUser(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Finds user by email
     * @param email user email
     * @return user
     */
    public static User getUserByEmail(String email){
        User user=null;
        String sql="SELECT * FROM users WHERE email=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setString(1,email);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        user= getUser(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * Adds new user to database
     * @param user user object
     * @return row count for SQL data
     */
    public static int addUser(User user){
        String sql="INSERT INTO users (id, name, email, password, money, status) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,user.getId());
                    preparedStatement.setString(2,user.getName());
                    preparedStatement.setString(3,user.getEmail());
                    preparedStatement.setString(4,user.getPassword());
                    preparedStatement.setInt(5,user.getMoney());
                    preparedStatement.setString(6,user.getStatus().toString().toLowerCase());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes user from database
     * @param id id of user
     * @return row count for SQL data
     */
    public static int removeUser(int id){
        String sql="DELETE FROM users WHERE id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Updates user data
     * @param user user object
     * @return row count for SQL data
     */
    public static int updateUser(User user){
        String sql="UPDATE users SET name=?, password=?, money=?, status=? WHERE id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setString(1,user.getName());
                    preparedStatement.setString(2,user.getPassword());
                    preparedStatement.setInt(3,user.getMoney());
                    preparedStatement.setString(4,user.getStatus().toString().toLowerCase());
                    preparedStatement.setInt(5,user.getId());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static User getUser(ResultSet resultSet) throws SQLException{
        User user=new User();
        user.setId(resultSet.getInt(1));
        user.setName(resultSet.getString(2));
        user.setEmail(resultSet.getString(3));
        user.setPassword(resultSet.getString(4));
        user.setMoney(resultSet.getInt(5));
        String status=resultSet.getString(6);
        switch (status){
            case "admin": { user.setStatus(UserStatus.ADMIN); break; }
            case "user": { user.setStatus(UserStatus.USER); break; }
            case "banned":{ user.setStatus(UserStatus.BANNED); break;}
            default: { break; }
        }

        return user;
    }
}
