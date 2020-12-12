package com.project.Service;

import com.project.DB.OrderDB;
import com.project.entities.Car;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.OrderStatus;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.ArrayList;

public class OrderService {

    public ArrayList<Order> getOrders(){
        return OrderDB.getOrders();
    }

    public void addOrder(int id, User user, Car car,String startDate,String endDate,int duration){
        Order order=new Order();
        order.setId(id);
        order.setUser(user);
        order.setCar(car);
        order.setStatus(OrderStatus.AWAITING);
        order.setStartDate(startDate);
        order.setEndDate(endDate);
        order.setDuration(duration);
        OrderDB.addOrder(order);
    }

    public ArrayList<Order> getOrdersByStatus(String status){
        return OrderDB.getOrdersByStatus(status);
    }

    public ArrayList<Order> getOrdersByUserAndStatus(int userId, String status){
        return OrderDB.getOrdersByUserIdAndStatus(userId, status);
    }

    public Order getOrderById(int id){
        return OrderDB.getOrderById(id);
    }

    public void deleteOrder(int id){
        OrderDB.removeOrder(id);
    }

    public void updateOrderStatus(Order order){
        OrderDB.updateOrderStatus(order);
    }
}
