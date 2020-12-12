package com.project.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import com.project.DB.*;
import com.project.Service.CarService;
import com.project.Service.UserService;
import com.project.entities.Car;
import com.project.entities.User;

@WebServlet("/ViewCarsServlet")
public class ViewCarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        CarService carService=new CarService();
        HttpSession session = request.getSession();
        ArrayList<Car> cars = null;
        RequestDispatcher dispatcher = null;
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user = userService.getUser(user.getId());
            session.setAttribute("user", user);
            String value = "";
            int range = 0;
            switch (user.getStatus()) {
                case USER: {
                    try {
                        value = (String) session.getAttribute("value");
                        range = (Integer) session.getAttribute("range");
                    } catch (Exception e) {
                        value = "";
                        range = 0;
                    }

                    session.setAttribute("value", value);
                    session.setAttribute("range", range);
                    if (value == "" && range == 0) {
                        cars = carService.getCars();
                    }
                    cars=carService.selectCars(value,range);
                    dispatcher = getServletContext()
                            .getRequestDispatcher("/user/viewCars.jsp");
                    break;
                }
                case ADMIN: {
                    cars = carService.getCars();
                    dispatcher = getServletContext()
                            .getRequestDispatcher("/admin/viewCars.jsp");
                    break;
                }
                default: {

                    break;
                }
            }
        } else {
            cars = (ArrayList<Car>) request.getAttribute("cars");
            if(cars==null){
                cars=carService.getCars();
            }
            dispatcher = getServletContext()
                    .getRequestDispatcher("/guest/viewCars.jsp");
        }
        request.setAttribute("cars", cars);
        dispatcher.forward(request, response);
    }
}