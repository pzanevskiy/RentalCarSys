package com.project.DB;

import com.project.entities.Car;
import com.project.enums.CarStatus;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class CarDB {

    private static final String url="jdbc:mysql://localhost:3306/rentcar_db?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String root="root";
    private static final String password="qw12QW34";

    /**
     * Show all cars from database
     * @return List of cars
     * @throws SQLException
     */
    public static ArrayList<Car> getCars() {

        ArrayList<Car> cars=new ArrayList<Car>();
        String sql="SELECT * FROM cars";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,root,password)) {
                Statement statement=conn.createStatement();
                ResultSet resultSet=statement.executeQuery(sql);
                while (resultSet.next()){
                    Car car= getCar(resultSet);
                    cars.add(car);
                }
            }
        } catch (Exception ex){

        }

        return cars;
    }

    /**
     * Finds car from database by id
     * @param id id of car
     * @return car
     * @throws SQLException
     */
    public static Car getCarById(int id) {
        Car car=null;
        String sql="SELECT * FROM cars WHERE id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        car= getCar(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    /**
     * Adds new car to database
     * @param car car object
     * @return row count for SQL data
     * @throws SQLException
     */
    public static int addCar(Car car) {
        String sql="INSERT INTO cars (id, brand, model, price, status) VALUES (?, ?, ?, ?, ?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,car.getId());
                    preparedStatement.setString(2,car.getName());
                    preparedStatement.setString(3,car.getModel());
                    preparedStatement.setInt(4,car.getPrice());
                    preparedStatement.setString(5,car.getStatus().toString().toLowerCase());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes car from database
     * @param id id of car
     * @return row count for SQL data
     */
    public static int removeCar(int id) {
        String sql="DELETE FROM cars WHERE id=?";
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
     * Updates car data
     * @param car car object
     * @return row count for SQL data
     * @throws SQLException
     */
    public static void updateCar(Car car) {
        String sql="UPDATE cars SET brand=?, model=?, price=?, status=? WHERE id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(5,car.getId());
                    preparedStatement.setString(1,car.getName());
                    preparedStatement.setString(2,car.getModel());
                    preparedStatement.setInt(3,car.getPrice());
                    preparedStatement.setString(4,car.getStatus().toString().toLowerCase());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getDistinctCarNames(){
        ArrayList<String> names = new ArrayList<>();
        String sql="SELECT DISTINCT brand FROM cars";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,root,password)) {
                Statement statement=conn.createStatement();
                ResultSet resultSet=statement.executeQuery(sql);
                while (resultSet.next()){
                    String name = resultSet.getString(1);
                    names.add(name);
                }
            }
        } catch (Exception ex){

        }
        return names;
    }

    private static Car getCar(ResultSet resultSet) throws SQLException{
        Car car=new Car();
        car.setId(resultSet.getInt(1));
        car.setName(resultSet.getString(2));
        car.setModel(resultSet.getString(3));
        car.setPrice(resultSet.getInt(4));
        String status=resultSet.getString(5);

        switch (status){
            case "free": { car.setStatus(CarStatus.FREE); break; }
            case "selected": { car.setStatus(CarStatus.SELECTED); break; }
            default: { break; }
        }

        return car;
    }
}
