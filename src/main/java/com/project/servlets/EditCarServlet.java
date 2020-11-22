package com.project.servlets;

import com.project.DB.CarDB;
import com.project.entities.Car;
import com.project.enums.CarStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/EditCarServlet")
public class EditCarServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(EditCarServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        Car car=null;
        car= CarDB.getCarById(id);
        car.setName(request.getParameter("brand"));
        car.setModel(request.getParameter("model"));
        car.setType(request.getParameter("type"));
        car.setPrice(Integer.parseInt(request.getParameter("price")));
        String status=request.getParameter("status").toLowerCase();
        switch (status){
            case "free": { car.setStatus(CarStatus.FREE); break; }
            case "selected": { car.setStatus(CarStatus.SELECTED); break; }
            default: { break; }
        }

        CarDB.updateCar(car);
        LOG.info("car "+car.getId()+" upd");
        response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("ide"));
        Car car=null;
        car= CarDB.getCarById(id);
        request.setAttribute("car",car);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/editCar.jsp");
        dispatcher.forward(request, response);
    }
}
