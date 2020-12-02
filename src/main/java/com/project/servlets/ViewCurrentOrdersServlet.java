package com.project.servlets;

import com.project.DB.OrderDB;
import com.project.DB.UserDB;
import com.project.Service.DateService;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.OrderStatus;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;

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

@WebServlet("/ViewCurrentOrdersServlet")
public class ViewCurrentOrdersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int id=Integer.parseInt(request.getParameter("id"));

        switch (user.getStatus()){
            case ADMIN:{
                String status=request.getParameter("status");
                Order order=null;
                order=OrderDB.getOrderById(id);
                switch (status){
                    case "complete":{
                        order.setStatus(OrderStatus.COMPLETED);
                        OrderDB.updateOrderStatus(order);
                        response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
                        break;
                    }
//                    case "invoice":{
//                        session.setAttribute("id",id);
//                        response.sendRedirect(request.getContextPath()+"/AddInvoiceServlet");
//                        break;
//                    }
                    default:{
                        break;
                    }
                }
                break;
            }
            case USER:{
                Order order=null;
                order=OrderDB.getOrderById(id);
                order.setStatus(OrderStatus.RETURNED);
                OrderDB.updateOrderStatus(order);
                response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
                break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        switch (user.getStatus()){
            case ADMIN:{
                orders= OrderDB.getOrdersByStatus("in_process");
                request.setAttribute("orders",orders);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewCurOrders.jsp");
                break;
            }
            case USER:{
                orders=OrderDB.getOrdersByUserIdAndStatus(user.getId(),"in_process");
                request.setAttribute("orders",orders);
                dispatcher=getServletContext().getRequestDispatcher("/user/viewCurOrders.jsp");
                break;
            }
        }
        dispatcher.forward(request, response);
    }
}
