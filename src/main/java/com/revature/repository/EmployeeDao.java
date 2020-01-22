package com.revature.repository;

import java.util.List;
import com.revature.model.Employee;

public interface EmployeeDao {

  /**
   * Get a Employee by its id
   * 
   * @param id
   * @return
   */
  Employee get(int id);


  /**
   * Get a Employee by its Username
   * 
   * @param username
   * @return
   */
  List<Employee> getUserByUsername(String username);

  /**
   * Gets all Employee
   * 
   * @return
   */
  List<Employee> getAll();

  /**
   * Save a new Employee.
   * 
   * @param A employee not persisted yet
   */
  void save(Employee employee);

  /**
   * Update an existing Employee using its id.
   * 
   * @param employee
   */
  void update(Employee employee);

}
