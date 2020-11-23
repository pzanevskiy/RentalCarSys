package com.project.servlets;

import com.project.DB.CarDB;
import com.project.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/PreselectServlet")
public class PreselectServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        ArrayList<String> names= CarDB.getDistinctCarNames();
        request.setAttribute("names",names);
        RequestDispatcher dispatcher=getServletContext().getRequestDispatcher("/user/preselectCars.jsp");
        dispatcher.forward(request,response);
    }
}
