package com.project.servlets;

import com.project.DB.InvoiceDB;
import com.project.DB.OrderDB;
import com.project.DB.UserDB;
import com.project.Service.DateService;
import com.project.Service.InvoiceService;
import com.project.Service.OrderService;
import com.project.Service.UserService;
import com.project.entities.Invoice;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.InvoiceStatus;
import com.project.enums.OrderStatus;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
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
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        InvoiceService invoiceService = new InvoiceService();
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        int id=Integer.parseInt(request.getParameter("id"));

        switch (user.getStatus()){
            case ADMIN:{
                String status=request.getParameter("status");
                Order order=null;
                order=orderService.getOrderById(id);
                switch (status){
                    case "complete":{
                        order.setStatus(OrderStatus.COMPLETED);
                        orderService.updateOrderStatus(order);
                        response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
                        break;
                    }
                    case "invoice":{
                        int difference=DateService.getDaysDifference(order.getEndDate());
                        if(difference>0){
                            Invoice invoice=new Invoice(order,order.getUser().getId(),difference*order.getCar().getPrice(),"Lease debt", InvoiceStatus.NOT_PAID);
                            invoiceService.addInvoice(invoice);
                        }
                        order.setStatus(OrderStatus.COMPLETED);
                        orderService.updateOrderStatus(order);
                        response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
                        break;
                    }
                    default:{
                        break;
                    }
                }
                break;
            }
            case USER:{
                Order order=null;
                order=orderService.getOrderById(id);
                order.setStatus(OrderStatus.RETURNED);
                orderService.updateOrderStatus(order);
                response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
                break;
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        user= userService.getUser(user.getId());
        session.setAttribute("user",user);
        switch (user.getStatus()){
            case ADMIN:{
                orders= orderService.getOrdersByStatus("in_process");
                request.setAttribute("orders",orders);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewCurOrders.jsp");
                break;
            }
            case USER:{
                orders=orderService.getOrdersByUserAndStatus(user.getId(),"in_process");
                request.setAttribute("orders",orders);
                dispatcher=getServletContext().getRequestDispatcher("/user/viewCurOrders.jsp");
                break;
            }
        }
        dispatcher.forward(request, response);
    }
}
