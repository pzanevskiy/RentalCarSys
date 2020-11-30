package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.OrderDB;
import com.project.Service.DateService;
import com.project.entities.Car;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.OrderStatus;
import org.apache.log4j.Logger;
import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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
        session.setAttribute("car",car);
        session.setAttribute("dur",dur);
        if(dur*car.getPrice()<user.getMoney()){
            Order order=new Order();
            ArrayList<Order> orders=null;
            orders= OrderDB.getOrders();
            int id=orders.get(orders.size()-1).getId()+1;
            session.setAttribute("orderId",id);
            int userId=user.getId();
            order.setId(id);
            order.setUser(user);
            order.setCar(car);
            order.setStatus(OrderStatus.AWAITING);
            order.setDuration(dur); /*Integer.parseInt(request.getParameter("dur"))*/
            LocalDateTime localDateTime=LocalDateTime.now(DateTimeZone.forID("Europe/Minsk"));
            order.setDate(DateService.getParsedDate(localDateTime));
            session.setAttribute("begin",DateService.getParsedDate(localDateTime));
            session.setAttribute("end",DateService.getParsedDate(DateService.getAfterDurationDateTime(localDateTime,dur)));
            OrderDB.addOrder(order);
            LOG.info(user.getName()+" rent "+car.getName()+" "+car.getModel());
        }
        response.sendRedirect(request.getContextPath()+"/RentCarServlet");

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        //int id=Integer.parseInt(request.getParameter("id"));
        Car car=(Car) session.getAttribute("car");
        User user = (User) session.getAttribute("user");
        int dur=(int)session.getAttribute("dur");
        request.setAttribute("orderId",session.getAttribute("orderId"));
        request.setAttribute("total",dur* car.getPrice());
        request.setAttribute("dur",dur);
        request.setAttribute("car",car);
        request.setAttribute("user",user);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/rentCar.jsp");
        dispatcher.forward(request, response);
    }
}
