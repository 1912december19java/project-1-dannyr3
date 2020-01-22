package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Employee;

public class EmployeeDaoPostgres implements EmployeeDao {

  private static Connection conn;

  static {
    // this explicitly loads the Driver Class:
    try {
      Class.forName("org.postgresql.Driver"); // loads our driver class
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }

    try {
      conn = DriverManager.getConnection(System.getenv("connstring"), System.getenv("username"),
          System.getenv("password"));
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public Employee get(int id) {
    Employee out = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");
      // 1 is the index of the ?
      stmt.setInt(1, id);
      // Now our statement is ready to go. Lets run it.
      if (stmt.execute()) {
        // Now we have some results. Lets get them.
        rs = stmt.getResultSet();
      }
      // This line is typical, but not particularly useful for our 1-line rs
      while (rs.next()) {
        out = new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
            rs.getString("firstName"), rs.getString("lastName"), rs.getBoolean("isManager"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return out;
  }

  @Override
  public List<Employee> getUserByUsername(String username) {
    List<Employee> allEmployees = new ArrayList<Employee>();
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM employee WHERE username = ?"); // ? is index 1
      stmt.setString(1, username);
      
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      
      while (rs.next()) {
        allEmployees.add(new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
            rs.getString("firstName"), rs.getString("lastName"), rs.getBoolean("isManager")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return allEmployees;
  }

  @Override
  public List<Employee> getAll() {
    List<Employee> allEmployees = new ArrayList<Employee>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt = conn.prepareStatement("SELECT * FROM employee");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allEmployees.add(new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
            rs.getString("firstName"), rs.getString("lastName"), rs.getBoolean("isManager")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return allEmployees;
  }

  @Override
  public void save(Employee employee) {
    
    PreparedStatement stmt = null;
    
    try {
      stmt = conn.prepareStatement(
          "INSERT INTO employee (username, password, firstName, lastName, isManager) VALUES ( ?, ?, ?, ?, ?)");
      stmt.setString(1, employee.getUsername());
      stmt.setString(2, employee.getPassword());
      stmt.setString(3, employee.getFirstName());
      stmt.setString(4, employee.getLastName());
      stmt.setBoolean(5, employee.isManager());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(Employee employee) {
    
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE employee SET username = ?, password = ?, firstName = ?, lastName = ? WHERE id = ?");
      stmt.setString(1, employee.getUsername());
      stmt.setString(2, employee.getPassword());
      stmt.setString(3, employee.getFirstName());
      stmt.setString(4, employee.getLastName());
      stmt.setInt(5, employee.getId());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
