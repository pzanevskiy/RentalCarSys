package com.project.entities;

import com.project.enums.UserStatus;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private int money;
    private UserStatus status;

    public User(){

    }

    public User(int id, String name, String email, String password, int money, UserStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.money = money;
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
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User " +
                "name: " + name + ' ' +
                ", email: " + email +
                ". Money: " + money +
                '.';
    }
}
