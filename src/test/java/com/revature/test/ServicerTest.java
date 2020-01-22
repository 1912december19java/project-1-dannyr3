package com.revature.test;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.revature.model.Employee;
import com.revature.model.Reinbursement;
import com.revature.model.Employee;
import com.revature.service.*;

public class ServicerTest {

  private static Servicer service;
  private static String input;
  private static String output;
  private static Boolean outputBol;
  private static Employee employee;
  
  
  @Before // before it Test runs
  public void setUp() {
    service = new Servicer();
  }

  @Test
  public void loginValidate2Test() {
    boolean out = service.loginValidate("dannyr3@vt.edu", "Dannyr3");
    assertTrue(out);
  }

  @Test
  public void loginValidate2Test2() {
    boolean out = service.loginValidate("d", "danny");
    assertFalse(out);
  }

  @Test
  public void loginValidate2Test3() {
    boolean out = service.loginValidate("da", "da");
    assertFalse(out);
  }
 
  @Test
  public void getRTest() {
    Reinbursement out = service.getR(1);
    assertTrue(out.getId() == 1);
  }
  
  @Test
  public void getAllRTest(){
    List<Reinbursement> out = service.getAllR();
    assertFalse(out.isEmpty());
  }
  
  @Test
  public void getReinbursementsByEmployeeIdTest(){
    List<Reinbursement> out = service.getReinbursementsByEmployeeId(0);
    assertFalse(out.isEmpty());
  }
  
  //@Test
  //public void createReinbursement(Reinbursement reinbursement) {
  //  reinbursements.save(reinbursement);
  //}
  
  //@Test
  //public void updateReinbursement(Reinbursement reinbursement)
  //{
  //  reinbursements.update(reinbursement);
  //}
  
  @Test
  public void getETest() {
    Employee out = service.getE(0);
    assertTrue(out.getId() == 0);
  }

  @Test
  public void getUserByUsernameTest(){
    List<Employee> out = service.getUserByUsername("dannyr3@vt.edu");
    assertFalse(out.isEmpty());
  }

  @Test
  public void getAllETest(){
    List<Employee> out = service.getAllE();
    assertFalse(out.isEmpty());
  }
  
  //@Test
  //public void createEmployee(Employee employee) {
  //  employees.save(employee);
  //}
  
  //@Test
  //public void updateEmployee(Employee employee) {
  //  employees.update(employee);
  //}
  
}
