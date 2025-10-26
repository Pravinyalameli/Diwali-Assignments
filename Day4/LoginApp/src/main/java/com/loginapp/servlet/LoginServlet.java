package com.loginapp.servlet;


import com.loginapp.dao.UserDAO;
import com.loginapp.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
 private UserDAO userDAO = new UserDAO();
 
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
     
     String username = request.getParameter("username");
     String password = request.getParameter("password");
     
     User user = userDAO.validateUser(username, password);
     
     if (user != null) {
         // Login successful
         HttpSession session = request.getSession();
         session.setAttribute("user", user);
         response.sendRedirect("welcome.jsp");
     } else {
         // Login failed
         response.sendRedirect("error.jsp");
     }
 }
}