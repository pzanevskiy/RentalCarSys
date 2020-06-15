package com.project.servlets;

import com.project.DB.OrderDB;
import com.project.entities.Order;
import com.project.enums.OrderStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ViewReturnedOrdersServlet")
public class ViewReturnedOrdersServlet extends HttpServlet {
    private static final Logger LOG= Logger.getLogger(ViewReturnedOrdersServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=null;
        order=OrderDB.getOrderById(id);
        String status=request.getParameter("status");
        switch (status){
            case "repair":{
                int repairPrice=Integer.parseInt(request.getParameter("repair"));
                String msg=request.getParameter("message");
                order.setStatus(OrderStatus.REPAIR);
                order.setRepairPrice(repairPrice);
                order.setMessage(msg);
                OrderDB.updateOrderStatusRep(order);
                LOG.info("order "+ order.getId()+" change status to repair");
                break;
            }
            case "completed":{
                order.setStatus(OrderStatus.COMPLETED);
                OrderDB.updateOrderStatus(order);
                LOG.info("user "+order.getUser().getName()+" complete order "+order.getId());
                break;
            }
            default:{
                break;
            }
        }
        response.sendRedirect(request.getContextPath()+"/ViewReturnedOrdersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        orders= OrderDB.getOrdersByStatus("returned");
        String check="full";
        if(orders.size()>0){
            check="emp";
        }
        request.setAttribute("check",check);
        request.setAttribute("orders",orders);
        dispatcher=getServletContext().getRequestDispatcher("/admin/viewReturnOrders.jsp");
        dispatcher.forward(request,response);
    }
}
