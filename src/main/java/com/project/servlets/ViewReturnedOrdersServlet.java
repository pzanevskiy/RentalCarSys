package com.project.servlets;

import com.project.DB.InvoiceDB;
import com.project.DB.OrderDB;
import com.project.Service.DateService;
import com.project.Service.InvoiceService;
import com.project.Service.OrderService;
import com.project.Service.UserService;
import com.project.entities.Invoice;
import com.project.entities.Order;
import com.project.enums.InvoiceStatus;
import com.project.enums.OrderStatus;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        InvoiceService invoiceService=new InvoiceService();
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=null;
        order=orderService.getOrderById(id);
        String status=request.getParameter("status");
        switch (status){
            case "invoice":{
                int repairPrice=Integer.parseInt(request.getParameter("repair"));
                String msg=request.getParameter("msg");
                int difference= DateService.getDaysDifference(order.getEndDate());
                if(difference>0){
                    Invoice invoice=new Invoice(order,order.getUser().getId(),difference*order.getCar().getPrice()+repairPrice,"Lease debt. "+msg, InvoiceStatus.NOT_PAID);
                    invoiceService.addInvoice(invoice);
                }else{
                    Invoice invoice=new Invoice(order,order.getUser().getId(),repairPrice,msg, InvoiceStatus.NOT_PAID);
                    invoiceService.addInvoice(invoice);
                }
                order.setStatus(OrderStatus.COMPLETED);
                orderService.updateOrderStatus(order);
                break;
            }
            case "complete":{
                order.setStatus(OrderStatus.COMPLETED);
                orderService.updateOrderStatus(order);
                break;
            }
            default:{
                break;
            }
        }
        response.sendRedirect(request.getContextPath()+"/ViewReturnedOrdersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        orders= orderService.getOrdersByStatus("returned");
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
