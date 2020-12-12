package com.project.servlets;

import com.project.DB.InvoiceDB;
import com.project.DB.OrderDB;
import com.project.DB.UserDB;
import com.project.Service.InvoiceService;
import com.project.Service.OrderService;
import com.project.Service.UserService;
import com.project.entities.Invoice;
import com.project.entities.Order;
import com.project.entities.User;
import com.project.enums.InvoiceStatus;
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
import java.util.List;

@WebServlet("/ViewInvoicesServlet")
public class ViewInvoicesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        Invoice invoice = null;
        User user = new User();
        user = (User) session.getAttribute("user");
        invoice = InvoiceDB.getInvoiceById(id);
        invoice.setInvoiceStatus(InvoiceStatus.PAID);
        int userMoney = user.getMoney();
        userMoney = userMoney - invoice.getPrice();
        user.setMoney(userMoney);
        UserDB.updateUser(user);
        InvoiceDB.updateInvoiceStatus(InvoiceStatus.PAID, invoice.getId());
        response.sendRedirect(request.getContextPath() + "/ViewInvoicesServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        OrderService orderService = new OrderService();
        InvoiceService invoiceService = new InvoiceService();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        ArrayList<Order> orders = null;
        List<Invoice> invoices = null;
        List<Invoice> invoicesP = null;
        RequestDispatcher dispatcher = null;
        switch (user.getStatus()) {
            case ADMIN: {
                invoices = invoiceService.getInvoices();
                request.setAttribute("invoices", invoices);
                dispatcher = getServletContext().getRequestDispatcher("/admin/viewInvoices.jsp");
                break;
            }
            case USER: {
                invoices = invoiceService.getInvoiceByStatus(InvoiceStatus.NOT_PAID, user.getId());
                invoicesP = invoiceService.getInvoiceByStatus(InvoiceStatus.PAID, user.getId());
                request.setAttribute("invoices", invoices);
                request.setAttribute("invoicesP", invoicesP);
                dispatcher = getServletContext().getRequestDispatcher("/user/viewInvoices.jsp");
                break;
            }
        }
        user = userService.getUser(user.getId());
        session.setAttribute("user", user);
        dispatcher.forward(request, response);
    }
}
