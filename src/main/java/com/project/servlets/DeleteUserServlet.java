package com.project.servlets;

import com.project.DB.UserDB;
import com.project.entities.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(DeleteUserServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id=Integer.parseInt(request.getParameter("id"));
        User user=UserDB.getUserById(id);
        UserDB.removeUser(id);

        LOG.info("user "+user.getName()+" deleted");
        response.sendRedirect(request.getContextPath()+"/ViewUsersServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
