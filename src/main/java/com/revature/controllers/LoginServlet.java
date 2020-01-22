package com.revature.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.revature.service.Servicer;

public class LoginServlet extends HttpServlet{
  
  private Servicer servicer = new Servicer();
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    
    
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    System.out.println("Got the Post");
    req.getMethod();
    String username = req.getParameter("username");
    String password = req.getParameter("password");
    System.out.println(username + " " + password);
    
    if (servicer.loginValidate(username, password)) {
      req.getSession().setAttribute("eId", servicer.employee.getId());
      req.getSession().setAttribute("eUsername", servicer.employee.getUsername());
      req.getSession().setAttribute("ePassword", servicer.employee.getPassword());
      req.getSession().setAttribute("eFirstName", servicer.employee.getFirstName());
      req.getSession().setAttribute("eLastName", servicer.employee.getLastName());
      req.getSession().setAttribute("eIsManager", servicer.employee.isManager());
      
      if (servicer.employee.isManager()) {
        resp.sendRedirect("manager.html?Id=" + servicer.employee.getId());
      } else {
        resp.sendRedirect("employee.html?Id=" + servicer.employee.getId());
      }
      //send to manager or employee page depending on is manager
      //resp.sendRedirect("index.jsp"+"?resonse=" + username);
      //req.getRequestDispatcher("index.jsp"+"?resonse=" + username).forward(req, resp);
    } else {
      //send back to login page
      //System.out.println(resp.getHeaderNames().toString());
      resp.getWriter().println("<div style='font-size:30px; color:red'>"+"Please Enter the Correct Username or Password"+"</div>");
      req.getRequestDispatcher("Index.html").include(req, resp);
      //req.getRequestDispatcher("Index.html").forward(req, resp);
      //resp.sendRedirect("Index.html");
     
    }
    
  }

}
