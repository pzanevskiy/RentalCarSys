package com.project.servlets;

import com.project.DB.UserDB;
import com.project.entities.User;
import com.project.enums.UserStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BanUserServlet")
public class BanUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        User user=null;
        user = UserDB.getUserById(id);
        switch (user.getStatus()){
            case USER:{
                user.setStatus(UserStatus.BANNED);
                UserDB.updateUser(user);
                break;
            }
            case BANNED:{
                user.setStatus(UserStatus.USER);
                UserDB.updateUser(user);
                break;
            }
            default:{break;}
        }

        response.sendRedirect(request.getContextPath()+"/ViewUsersServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
