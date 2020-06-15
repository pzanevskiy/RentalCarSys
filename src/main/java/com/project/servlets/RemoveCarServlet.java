package com.project.servlets;

import com.project.DB.CarDB;
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
    private static final Logger LOG=Logger.getLogger(RemoveCarServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        Car car=CarDB.getCarById(id);
        LOG.info("car "+car.getName()+" "+car.getModel()+" removed");
        CarDB.removeCar(id);
        response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
