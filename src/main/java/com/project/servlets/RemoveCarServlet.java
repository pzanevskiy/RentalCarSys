package com.project.servlets;

import com.project.DB.CarDB;
import com.project.Service.CarService;
import com.project.entities.Car;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RemoveCarServlet")
public class RemoveCarServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarService carService=new CarService();
        int id=Integer.parseInt(request.getParameter("id"));
        carService.deleteCar(id);
        response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
