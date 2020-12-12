package com.project.servlets;

import com.project.DB.UserDB;
import com.project.Service.UserService;
import com.project.entities.User;
import com.project.enums.UserStatus;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        UserService userService=new UserService();
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        User user=null;
        UserStatus status=null;
        user= userService.getUserByEmail(email);

        if(user!=null && !password.equals(user.getPassword())){
            user=null;
            String path=request.getContextPath();
            response.sendRedirect(path);
        }

        if(user!=null && password.equals(user.getPassword())){
            status= user.getStatus();
            session.setAttribute("user",user);
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
        }else{
            session.setAttribute("guest","guestUser");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/guest/guest.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UserService userService=new UserService();
        User user = (User)session.getAttribute("user");
        if(user!=null){
            user=userService.getUser(user.getId());
            UserStatus status=null;
            status=user.getStatus();
            switch (status){
                case USER:{
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user/user.jsp");
                    session.setAttribute("user",user);
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
        }else{
            session.setAttribute("guest","guestUser");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/guest/guest.jsp");
            dispatcher.forward(request, response);
        }


    }
}
