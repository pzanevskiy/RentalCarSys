package com.project.Service;

import com.project.DB.UserDB;
import com.project.entities.Car;
import com.project.entities.User;
import com.project.enums.UserStatus;

import java.util.ArrayList;

public class UserService {

    public User addUser(String name, String pass, String email, String money){
        User user = new User();
        ArrayList<User> users = new ArrayList<User>();
        users = UserDB.getUsers();
        int id = users.get(users.size() - 1).getId() + 1;
        user.setId(id);
        user.setName(name);
        user.setPassword(pass);
        user.setEmail(email);
        user.setMoney(Integer.parseInt(money));
        user.setStatus(UserStatus.USER);
        UserDB.addUser(user);
        return user;
    }

    public void changeUserStatus(int id){
        User user=null;
        user = UserDB.getUserById(id);
        switch (user.getStatus()){
            case USER:{
                user.setStatus(UserStatus.BANNED);
                UserDB.updateUser(user);
                break;
            }
            case BANNED:{
                user.setStatus(UserStatus.USER);
                UserDB.updateUser(user);
                break;
            }
            default:{break;}
        }
    }

    public void deleteUser(int id){
        User user=UserDB.getUserById(id);
        UserDB.removeUser(id);
    }

    public ArrayList<User> getAllUsers(){
       return UserDB.getUsers();
    }

    public User getUser(int id){
        return  UserDB.getUserById(id);
    }

    public User getUserByEmail(String email){
        return UserDB.getUserByEmail(email);
    }

    public boolean checkMoney(int dur, Car car, User user){
        return dur*car.getPrice()<user.getMoney();
    }

    public void updateUser(User user){
        UserDB.updateUser(user);
    }
}
