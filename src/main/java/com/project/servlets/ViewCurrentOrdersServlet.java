package com.project.servlets;

import com.project.DB.OrderDB;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.OrderStatus;

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
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=null;
        order=OrderDB.getOrderById(id);
        order.setStatus(OrderStatus.RETURNED);
        OrderDB.updateOrderStatus(order);
        response.sendRedirect(request.getContextPath()+"/ViewCurrentOrdersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        orders= OrderDB.getOrdersByUserIdAndStatus(user.getId(),"in_process");
        request.setAttribute("orders",orders);
        dispatcher=getServletContext().getRequestDispatcher("/user/viewCurOrders.jsp");
        dispatcher.forward(request,response);
    }
}
