package com.project.entities;

import com.project.enums.OrderStatus;

public class Order {

    private int id;
    private User user;
    private Car car;
    private OrderStatus status;
    private int duration;
    private String startDate;
    private String endDate;

    public Order() {
    }

    public Order(int id, User user, Car car, OrderStatus status,int duration, String startDate, String endDate) {
        this.id = id;
        this.user = user;
        this.car = car;
        this.status = status;
        this.duration=duration;
        this.startDate=startDate;
        this.endDate=endDate;
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

    public int getDuration(){
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
