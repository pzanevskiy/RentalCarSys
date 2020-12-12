package com.project.servlets;

import com.project.DB.OrderDB;
import com.project.DB.UserDB;
import com.project.Service.OrderService;
import com.project.Service.UserService;
import com.project.entities.Order;
import com.project.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ViewCanceledOrdersServlet")
public class ViewCanceledOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        OrderService orderService=new OrderService();
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        user= userService.getUser(user.getId());
        session.setAttribute("user",user);
        switch (user.getStatus()){
            case ADMIN:{
                orders= orderService.getOrdersByStatus("rejected");
                request.setAttribute("orders",orders);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewCancelOrders.jsp");
                break;
            }
            case USER:{
                orders=orderService.getOrdersByUserAndStatus(user.getId(),"rejected");
                request.setAttribute("orders",orders);
                dispatcher=getServletContext().getRequestDispatcher("/user/viewCancelOrders.jsp");
                break;
            }
        }
        dispatcher.forward(request, response);
    }
}
