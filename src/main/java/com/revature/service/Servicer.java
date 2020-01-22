package com.revature.service;

import java.util.List;
import com.revature.model.*;
import com.revature.repository.*;


public class Servicer {

  public Employee employee;
  public Reinbursement reinbursement;
  public static EmployeeDao employees = new EmployeeDaoPostgres();
  public static ReinbursementDao reinbursements = new ReinbursementDaoPostgres();
  
  public Servicer() {
    super();
    employee = null;
    reinbursement = null;
  }
  
  public boolean loginValidate(String username, String password) {

    for (Employee a : employees.getUserByUsername(username)) {
      if (a.getUsername().equals(username)) {
        Employee b = a;
        employee = a;

        if (b.getPassword().equals(password)) {
          return true;
        } else {
          return false;
        }
      } else {
        return false;
      }
    }
    return false;
  }
  
  public Reinbursement getR(int id) {
    return reinbursements.get(id);
  }
  
  public List<Reinbursement> getAllR(){
    return reinbursements.getAll();
  }
  
  public List<Reinbursement> getReinbursementsByEmployeeId(int employeeId){
    return reinbursements.getReinbursementsByEmployeeId(employeeId);
  }
  
  public void createReinbursement(Reinbursement reinbursement) {
    reinbursements.save(reinbursement);
  }
  
  public void updateReinbursement(Reinbursement reinbursement)
  {
    reinbursements.update(reinbursement);
  }
  
  public Employee getE(int id) {
    return employees.get(id);
  }

  public List<Employee> getUserByUsername(String username){
    return employees.getUserByUsername(username);
  }

  public List<Employee> getAllE(){
    return employees.getAll();
  }
  
  public void createEmployee(Employee employee) {
    employees.save(employee);
  }
  
  public void updateEmployee(Employee employee) {
    employees.update(employee);
  }
  
}
