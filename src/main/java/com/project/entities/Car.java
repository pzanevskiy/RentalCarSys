package com.project.entities;

import com.project.enums.CarStatus;

public class Car {
    private int id;
    private String name;
    private String model;
    private int price;
    private CarStatus status;


    public Car(){

    }

    public Car(int id, String name,String model, int price, CarStatus status) {
        this.id = id;
        this.name=name;
        this.model = model;
        this.price = price;
        this.status = status;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public CarStatus getStatus() {
        return status;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Car: " + name + ' ' +
                ", model " + model +
                ". Price:" + price;
    }
}
