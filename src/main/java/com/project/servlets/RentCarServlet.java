package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.OrderDB;
import com.project.DB.UserDB;
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
        int dur;
        String start=request.getParameter("start");
        String end=request.getParameter("end");
        LocalDateTime startDateTime= new LocalDateTime(start);
        LocalDateTime endDateTime = new LocalDateTime(end);

        //DateTime startDate=DateService.
        Car car=null;
        car= CarDB.getCarById(carId);
        dur=Days.daysBetween(startDateTime,endDateTime).getDays();
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
            order.setStartDate(DateService.getParsedDate(startDateTime));
            order.setEndDate(DateService.getParsedDate(endDateTime));
            session.setAttribute("begin",order.getStartDate());
            session.setAttribute("end",order.getEndDate());
            order.setDuration(dur); /*Integer.parseInt(request.getParameter("dur"))*/
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
        user= UserDB.getUserById(user.getId());
        session.setAttribute("user",user);
        request.setAttribute("orderId",session.getAttribute("orderId"));
        request.setAttribute("total",dur * car.getPrice());
        request.setAttribute("dur",dur);
        request.setAttribute("car",car);
        request.setAttribute("user",user);
        request.setAttribute("begin",session.getAttribute("begin"));
        request.setAttribute("end",session.getAttribute("end"));
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/rentCar.jsp");
        dispatcher.forward(request, response);
    }
}
