package com.project.entities;

import com.project.enums.OrderStatus;

public class Order {

    private int id;
    private User user;
    private Car car;
    private OrderStatus status;
    private int repairPrice;
    private int duration;
    private String message;
    private String date;

    public Order() {
    }

    public Order(int id, User user, Car car, OrderStatus status,int repairPrice,int duration,String message, String date) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.status = status;
        this.repairPrice=repairPrice;
        this.duration=duration;
        this.message=message;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getRepairPrice(){
        return repairPrice;
    }

    public void setRepairPrice(int repairPrice){
        this.repairPrice=repairPrice;
    }

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
