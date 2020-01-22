package com.revature.controllers;

import java.util.List;
import java.sql.Timestamp;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Employee;
import com.revature.model.Reinbursement;
import com.revature.service.Servicer;

public class EmployeeServlet extends HttpServlet{
 
  private Servicer service;
  private ObjectMapper om;
  
  @Override
  public void init() throws ServletException {
    this.service =  new Servicer();
    this.om = new ObjectMapper();
    super.init();
  }
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    String[] tokens = req.getRequestURI().split("/");
    
    if(tokens[3].equals("reinbursements")){
      boolean idSpecified = tokens.length > 4;
      if(idSpecified /*&& tokens[4].equals(service.employee.getId().toString())*/) {
        Integer id = Integer.valueOf(tokens[4]);
        List<Reinbursement> reinbursements = service.getReinbursementsByEmployeeId(id);
        if (reinbursements == null) {
          resp.setStatus(404);
        }
        resp.getWriter().write(om.writeValueAsString(reinbursements));
      } else {
        List<Reinbursement> reinbursements = service.getAllR();
        if (reinbursements == null) {
          resp.setStatus(404);
        }
        resp.getWriter().write(om.writeValueAsString(reinbursements));
      }
      
    }else if(tokens[3].contentEquals("employees")) {
      boolean idSpecified = tokens.length > 4;
      if(idSpecified) {
        Integer id = Integer.valueOf(tokens[4]);
        Employee employee = service.getE(id);
        if (employee == null) {
          resp.setStatus(404);
        }
        resp.getWriter().write(om.writeValueAsString(employee));
      } else {
        List<Employee> employees = service.getAllE();
        if (employees == null) {
          resp.setStatus(404);
        }
        resp.getWriter().write(om.writeValueAsString(employees));
      }
      
    } else {
      resp.setStatus(500);
    }
  }
  
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    
    String[] tokens = req.getRequestURI().split("/");
    
    switch(tokens[3]) {
      case "reinbursements":
        System.out.println(req.getReader().toString());
        Reinbursement receivedReinbursement = om.readValue(req.getReader(), Reinbursement.class);
        System.out.println(receivedReinbursement.getImage_location());
        try {
          service.createReinbursement(receivedReinbursement);
        } catch (Exception e) {
          resp.setStatus(400);
        }
        break;
        
      case "employees":
        Employee receivedEmployee = om.readValue(req.getReader(), Employee.class);
        
        try {
          service.updateEmployee(receivedEmployee);
        } catch (Exception e) {
          resp.setStatus(400);
        }
        break;
        
      case "reinsbursement":
        Reinbursement updateReinbursement = om.readValue(req.getReader(), Reinbursement.class);
        System.out.println(updateReinbursement.toString());

        try {
          service.updateReinbursement(updateReinbursement);
        } catch (Exception e) {
          resp.setStatus(400);
        }
        break;
        
     default:
       resp.setStatus(404);
    }
    
  }

}
