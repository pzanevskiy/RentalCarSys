package com.project.servlets;

import com.project.DB.OrderDB;
import com.project.DB.UserDB;
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
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ViewInvoicesServlet")
public class ViewInvoicesServlet extends HttpServlet {
    private static final Logger LOG= Logger.getLogger(ViewInvoicesServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        Order order =null;
        order=OrderDB.getOrderById(id);
        order.setStatus(OrderStatus.COMPLETED);
        int userMoney=order.getUser().getMoney();
        userMoney=userMoney-order.getRepairPrice();
        User user=new User();
        user=order.getUser();
        user.setMoney(userMoney);
        UserDB.updateUser(user);
        OrderDB.updateOrderStatus(order);
        LOG.info("user "+user.getName()+" pay for invoice and complete order "+order.getId());
        response.sendRedirect(request.getContextPath()+"/ViewInvoicesServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        switch (user.getStatus()){
            case ADMIN:{
                orders= OrderDB.getOrdersByNNRepair();
                request.setAttribute("repair","REPAIR");
                request.setAttribute("completed","COMPLETED");
                request.setAttribute("orders",orders);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewInvoices.jsp");
                break;
            }
            case USER:{
                orders=OrderDB.getOrdersByUserIdAndStatus(user.getId(),"repair");
                request.setAttribute("orders",orders);
                dispatcher=getServletContext().getRequestDispatcher("/user/viewInvoices.jsp");
                break;
            }
        }

        dispatcher.forward(request, response);
    }
}
