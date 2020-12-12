package com.project.servlets;

import com.project.DB.UserDB;
import com.project.Service.UserService;
import com.project.entities.User;
import com.project.enums.UserStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final Logger LOG = Logger.getLogger(AddUserServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService=new UserService();

        User user = userService.addUser(request.getParameter("name"),request.getParameter("pass"),
                request.getParameter("email"),request.getParameter("money"));

        if (session.getAttribute("guest") == "guestUser") {
            session.setAttribute("user",user);
            response.sendRedirect(request.getContextPath()+"/ViewCarsServlet");
        }else{
            LOG.info("new user created " + user.getName());
            response.sendRedirect(request.getContextPath());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/reg.jsp");
        dispatcher.forward(request, response);
    }
}
