package com.project.servlets;

import com.project.DB.UserDB;
import com.project.entities.User;
import com.project.enums.UserStatus;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/BanUserServlet")
public class BanUserServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(BanUserServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        User user=null;
        user = UserDB.getUserById(id);
        switch (user.getStatus()){
            case USER:{
                user.setStatus(UserStatus.BANNED);
                UserDB.updateUser(user);
                LOG.info("user "+user.getName()+" banned");
                break;
            }
            case BANNED:{
                user.setStatus(UserStatus.USER);
                UserDB.updateUser(user);
                LOG.info("user "+user.getName()+" unbanned");
                break;
            }
            default:{break;}
        }

        response.sendRedirect(request.getContextPath()+"/ViewUsersServlet");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
