package com.project.servlets;

import com.project.DB.CarDB;
import com.project.DB.UserDB;
import com.project.Service.UserService;
import com.project.entities.Car;
import com.project.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/ViewUsersServlet")
public class ViewUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService=new UserService();
        ArrayList<User> users= userService.getAllUsers();
        request.setAttribute("users",users);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/viewUsers.jsp");
        dispatcher.forward(request, response);
    }
}
