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

@WebServlet("/ViewAwaitOrdersServlet")
public class ViewAwaitOrdersServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(ViewAwaitOrdersServlet.class);
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
                    case "accept":{
                        order.setStatus(OrderStatus.IN_PROCESS);
                        int dur=order.getDuration();
                        int carPrice=order.getCar().getPrice();
                        int userMoney=order.getUser().getMoney();
                        int finalMoney=userMoney-(dur*carPrice);
                        User userP= UserDB.getUserById(order.getUser().getId());
                        userP.setMoney(finalMoney);
                        UserDB.updateUser(userP);
                        OrderDB.updateOrderStatus(order);
                        LOG.info("order "+order.getId()+" accepted");
                        break;
                    }
                    case "reject":{
                        order.setStatus(OrderStatus.REJECTED);
                        OrderDB.updateOrderStatus(order);
                        LOG.info("order "+order.getId()+" rejected");
                        break;
                    }
                    default:{
                        break;
                    }
                }
                break;
            }
            case USER:{
                Order order=OrderDB.getOrderById(id);
                LOG.info("user "+user.getName()+" removed order "+order.getId());
                OrderDB.removeOrder(id);
                break;
            }
        }

        response.sendRedirect(request.getContextPath()+"/ViewAwaitOrdersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<Order> orders=null;
        RequestDispatcher dispatcher=null;
        switch (user.getStatus()){
            case ADMIN:{
                orders= OrderDB.getOrdersByStatus("awaiting");
                request.setAttribute("orders",orders);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewAwaitOrders.jsp");
                break;
            }
            case USER:{
                orders=OrderDB.getOrdersByUserIdAndStatus(user.getId(),"awaiting");
                request.setAttribute("orders",orders);
                dispatcher=getServletContext().getRequestDispatcher("/user/viewAwaitOrders.jsp");
                break;
            }
        }
        dispatcher.forward(request, response);
    }
}
