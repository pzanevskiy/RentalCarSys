package com.project.servlets;

import com.project.DB.CarDB;
import com.project.entities.Car;
import com.project.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/PreselectServlet")
public class PreselectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        String value="";
        value=request.getParameter("val");
        int range=0;
        try{
            range=Integer.parseInt(request.getParameter("range"));

        }catch (Exception e) { }
        User user=(User)session.getAttribute("user");
        ArrayList<Car> cars=null;
        if(value!="" && range==0){
            cars=CarDB.getCarsByBrand(value);
        }
        if(value=="" && range>10){
            cars=CarDB.getCarsByRange(range);
        }
        if(value!="" && range>10){
            cars=CarDB.getCarsByBrandWithRange(value,range);
        }

       // cars=CarDB.getCars();
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
        if(cars==null || cars.size()==0){
            response.sendRedirect(request.getContextPath()+"/PreselectServlet");
        }else{
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<String> names= CarDB.getDistinctCarNames();
        request.setAttribute("names",names);
        RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/user/preselectCars.jsp");
        dispatcher.forward(request,response);
    }
}
