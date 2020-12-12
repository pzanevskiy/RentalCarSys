package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.UserDB;
import com.project.Service.CarService;
import com.project.Service.UserService;
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
        CarService carService=new CarService();
        HttpSession session=request.getSession();
        String value="";
        int range=0;
        try{
            value=(String)session.getAttribute("value");
            range=(Integer)session.getAttribute("range");
        }catch (Exception e) { }
        value=request.getParameter("val");
        try{
            range=Integer.parseInt(request.getParameter("range"));

        }catch (Exception e) { }

        session.setAttribute("value",value);
        session.setAttribute("range",range);
        ArrayList<Car> cars = carService.selectCars(value,range);
        request.setAttribute("cars",cars);
        RequestDispatcher dispatcher=null;
        User user=(User)session.getAttribute("user");
        if (user != null) {
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
        }else{
            dispatcher = getServletContext().getRequestDispatcher("/guest/viewCars.jsp");
        }
        if(cars==null || cars.size()==0){
            response.sendRedirect(request.getContextPath()+"/PreselectServlet");
        }else{
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        CarService carService=new CarService();
        HttpSession session=request.getSession();
        session.removeAttribute("value");
        session.removeAttribute("range");
        User user=(User)session.getAttribute("user");
        ArrayList<String> names= carService.getCarBrands();
        request.setAttribute("names",names);
        RequestDispatcher dispatcher=null;
        if(user!=null){
            user= userService.getUser(user.getId());
            session.setAttribute("user",user);
            dispatcher=getServletContext().getRequestDispatcher("/user/preselectCars.jsp");
        }else{
            dispatcher=getServletContext().getRequestDispatcher("/guest/preselectCars.jsp");
        }
        dispatcher.forward(request,response);
    }
}
