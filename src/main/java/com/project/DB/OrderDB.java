package com.project.DB;

import com.project.entities.Order;
import com.project.enums.OrderStatus;

import java.sql.*;
import java.util.ArrayList;

public class OrderDB {

    private static final String url="jdbc:mysql://localhost:3306/rentcar_db?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String root="root";
    private static final String password="qw12QW34";

    /**
     * Shows all orders from database
     * @return list of orders
     */
    public static ArrayList<Order> getOrders() {

        ArrayList<Order> orders=new ArrayList<Order>();
        String sql="SELECT * FROM orders";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,root,password)) {
                Statement statement=conn.createStatement();
                ResultSet resultSet=statement.executeQuery(sql);
                while (resultSet.next()){
                    Order order= getOrder(resultSet);
                    orders.add(order);
                }
            }
        } catch (Exception ex){        }

        return orders;
    }

    /**
     * Finds order from database by Id
     * @param id id of order
     * @return order object
     */
    public static Order getOrderById(int id){

        Order order=null;
        String sql="SELECT * FROM orders WHERE Id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        order= getOrder(resultSet);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }

    public static ArrayList<Order> getOrdersByUserIdAndStatus(int id,String status){
        ArrayList<Order> orders=new ArrayList<Order>();
        String sql="SELECT * FROM orders WHERE user_id=? AND status=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn= DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,id);
                    preparedStatement.setString(2,status);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                        Order order= getOrder(resultSet);
                        orders.add(order);
                    }
                }
            }
        } catch (Exception ex){        }
        return orders;
    }

    public static ArrayList<Order> getOrdersByStatus(String status){
        ArrayList<Order> orders=new ArrayList<Order>();
        String sql="SELECT * FROM orders WHERE status=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setString(1,status);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    while (resultSet.next()){
                            Order order= getOrder(resultSet);
                            orders.add(order);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    /**
     * Adds new order to database
     * @param order order object
     * @return rows count for SQL data
     */
    public static int addOrder(Order order){

        String sql="INSERT INTO orders (Id, user_id, car_id, status, duration, startD, endD) " +
                "VALUES (?, ?, ?, ?, ?, ? ,?)";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setInt(1,order.getId());
                    preparedStatement.setInt(2,order.getUser().getId());
                    preparedStatement.setInt(3,order.getCar().getId());
                    preparedStatement.setString(4,order.getStatus().toString().toLowerCase());
                    preparedStatement.setInt(5,order.getDuration());
                    preparedStatement.setString(6,order.getStartDate());
                    preparedStatement.setString(7,order.getEndDate());
                    return preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Deletes order from database
     * @param id order id
     * @return row count for SQL data
     */
    public static int removeOrder(int id) {
        String sql="DELETE FROM orders WHERE Id=?";
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
     * Updates order status
     * @param order order object
     */
    public static void updateOrderStatus(Order order){
        String sql="UPDATE orders SET status=? WHERE Id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setString(1,order.getStatus().toString().toLowerCase());
                    preparedStatement.setInt(2,order.getId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateOrderDates(Order order){
        String sql="UPDATE orders SET startD=?, endD=? WHERE Id=?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try(Connection conn=DriverManager.getConnection(url,root,password)) {
                try(PreparedStatement preparedStatement=conn.prepareStatement(sql)){
                    preparedStatement.setString(1,order.getStartDate());
                    preparedStatement.setString(2,order.getEndDate());
                    preparedStatement.setInt(3,order.getId());
                    preparedStatement.executeUpdate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static Order getOrder(ResultSet resultSet) throws SQLException {
        Order order=new Order();
        order.setId(resultSet.getInt(1));
        order.setUser(UserDB.getUserById(resultSet.getInt(2)));
        order.setCar(CarDB.getCarById(resultSet.getInt(3)));
        String status=resultSet.getString(4);
        switch (status){
            case "awaiting":{ order.setStatus(OrderStatus.AWAITING); break; }
            case "rejected":{ order.setStatus(OrderStatus.REJECTED); break; }
            case "in_process":{ order.setStatus(OrderStatus.IN_PROCESS); break; }
            case "completed":{ order.setStatus(OrderStatus.COMPLETED); break; }
            case "repair":{ order.setStatus(OrderStatus.REPAIR); break; }
            case "returned":{ order.setStatus(OrderStatus.RETURNED); break; }
            default: { break; }
        }
        order.setDuration(resultSet.getInt(5));
        order.setStartDate(resultSet.getString(6));
        order.setEndDate(resultSet.getString(7));
        return order;
    }
}
