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
import com.project.entities.Car;
import com.project.entities.User;

@WebServlet("/ViewCarsServlet")
public class ViewCarsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Car> cars=null;
        cars=CarDB.getCars();
        request.setAttribute("cars",cars);
        RequestDispatcher dispatcher=null;
        switch (user.getStatus()){
            case USER:{
                dispatcher = getServletContext()
                        .getRequestDispatcher("/user/viewCars.jsp"); break;
            }
            case ADMIN:{
                 dispatcher = getServletContext()
                        .getRequestDispatcher("/admin/viewCars.jsp"); break;
            }
            default:{
                break;
            }
        }
        dispatcher.forward(request, response);
    }
}