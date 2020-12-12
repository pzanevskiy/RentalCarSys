package com.project.servlets;

import com.project.DB.CarDB;
import com.project.Service.CarService;
import com.project.entities.Car;
import com.project.enums.CarStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/AddCarServlet")
public class AddCarServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(AddCarServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService=new CarService();
        Car car= carService.addCar(request.getParameter("brand"),request.getParameter("model"),request.getParameter("price"));
        LOG.info("admin add new car "+car.getName()+" "+car.getModel()+" "+car.getPrice());
        response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
