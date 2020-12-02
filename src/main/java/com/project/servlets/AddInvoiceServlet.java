package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.OrderDB;
import com.project.entities.Car;
import com.project.entities.Order;
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

@WebServlet("/AddInvoiceServlet")
public class AddInvoiceServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(AddInvoiceServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=new Order();
        order=OrderDB.getOrderById(id);
        int repairPrice=Integer.parseInt(request.getParameter("repair"));
        String msg=request.getParameter("msg");
        order.setStatus(OrderStatus.REPAIR);
        order.setRepairPrice(repairPrice);
        order.setMessage(msg);
        OrderDB.updateOrderStatusRep(order);
        LOG.info("order to repair "+order.getId()+" "+order.getUser().getName()+" "+order.getCar().getName()+" "+order.getRepairPrice());
        response.sendRedirect(request.getContextPath()+"/ViewReturnedOrdersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        int id=Integer.parseInt(request.getParameter("id"));
        Order order=null;
        order= OrderDB.getOrderById(id);
        request.setAttribute("order",order);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/invoiceForRepair.jsp");
        dispatcher.forward(request, response);
    }
}
