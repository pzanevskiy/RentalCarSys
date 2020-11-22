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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final Logger LOG=Logger.getLogger(LoginServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email=request.getParameter("email");
        String password=request.getParameter("password");
        User user=null;
        UserStatus status=null;
        user= UserDB.getUserByEmail(email);

        if(user!=null && !password.equals(user.getPassword())){
            user=null;
            String path=request.getContextPath();
            response.sendRedirect(path);
        }

        if(user!=null && password.equals(user.getPassword())){
            status= user.getStatus();
            HttpSession session=request.getSession();
            session.setAttribute("user",user);
            switch (status){
                case USER:{
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/user.jsp");
                    LOG.info("user "+user.getName()+" entered");
                    dispatcher.forward(request, response);
                    break;
                }
                case ADMIN:{
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/admin.jsp");
                    LOG.info("admin "+user.getName()+" entered");
                    dispatcher.forward(request, response);
                    break;
                }
                case BANNED:{
                    String path=request.getContextPath();
                    LOG.info("banned user try to enter");
                    response.sendRedirect(path);
                    break;
                }
                default:{
                    break;
                }
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        UserStatus status=null;
        status=user.getStatus();
        switch (status){
            case USER:{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/user.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case ADMIN:{
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/admin.jsp");
                dispatcher.forward(request, response);
                break;
            }
            case BANNED:{
                String path=request.getContextPath();
                response.sendRedirect(path);
                break;
            }
            default:{
                break;
            }
        }

    }
}
