package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.OrderDB;
import com.project.entities.Car;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.OrderStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/RentCarServlet")
public class RentCarServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(RentCarServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int carId=Integer.parseInt(request.getParameter("id"));
        int dur=Integer.parseInt(request.getParameter("dur"));
        Car car=null;
        car= CarDB.getCarById(carId);
        if(dur*car.getPrice()<user.getMoney()){
            Order order=new Order();
            ArrayList<Order> orders=null;
            orders= OrderDB.getOrders();
            int id=orders.get(orders.size()-1).getId()+1;
            int userId=user.getId();
            order.setId(id);
            order.setUser(user);
            order.setCar(car);
            order.setStatus(OrderStatus.AWAITING);
            order.setDuration(dur); /*Integer.parseInt(request.getParameter("dur"))*/
            OrderDB.addOrder(order);
            LOG.info(user.getName()+" rent "+car.getName()+" "+car.getModel());
        }
        response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Car car=null;
        car= CarDB.getCarById(id);
        request.setAttribute("car",car);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/rentCar.jsp");
        dispatcher.forward(request, response);
    }
}
