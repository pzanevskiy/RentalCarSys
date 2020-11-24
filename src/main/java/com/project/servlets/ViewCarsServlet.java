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

        RequestDispatcher dispatcher=null;
        switch (user.getStatus()){
            case USER:{
                String value="";
                int range=0;
                try{
                    value=(String)session.getAttribute("value");
                    range=(Integer)session.getAttribute("range");
                }catch (Exception e) { value=""; range=0;}

                session.setAttribute("value",value);
                session.setAttribute("range",range);
                if(value=="" && range==0){
                    cars=CarDB.getCars();
                }
                if(value!="" && range==0){
                    cars=CarDB.getCarsByBrand(value);
                }
                if(value=="" && range>10){
                    cars=CarDB.getCarsByRange(range);
                }
                if(value!="" && range>10){
                    cars=CarDB.getCarsByBrandWithRange(value,range);
                }
                dispatcher = getServletContext()
                        .getRequestDispatcher("/user/viewCars.jsp"); break;
            }
            case ADMIN:{
                 cars=CarDB.getCars();
                 dispatcher = getServletContext()
                        .getRequestDispatcher("/admin/viewCars.jsp"); break;
            }
            default:{
                break;
            }
        }
        request.setAttribute("cars",cars);
        dispatcher.forward(request, response);
    }
}