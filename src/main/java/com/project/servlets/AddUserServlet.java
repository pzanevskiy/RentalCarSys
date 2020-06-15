package com.project.servlets;

import com.project.DB.UserDB;
import com.project.entities.User;
import com.project.enums.UserStatus;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(AddUserServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user=new User();
        ArrayList<User> users =new ArrayList<User>();
        users=UserDB.getUsers();
        int id=users.get(users.size()-1).getId()+1;
        user.setId(id);
        user.setName(request.getParameter("name"));
        user.setPassword(request.getParameter("pass"));
        user.setEmail(request.getParameter("email"));
        user.setMoney(Integer.parseInt(request.getParameter("money")));
        user.setStatus(UserStatus.USER);
        UserDB.addUser(user);
        LOG.info("new user created "+user.getName());
        response.sendRedirect(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/reg.jsp");
        dispatcher.forward(request, response);
    }
}
