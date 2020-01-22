package com.revature.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.revature.model.Reinbursement;

public class ReinbursementDaoPostgres implements ReinbursementDao{
  
  private static Connection conn;

  // this guy will run when the class loads, after static fields are initialized.
  static {
    
    try {
      Class.forName("org.postgresql.Driver"); //loads our driver class
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
  public Reinbursement get(int id) {
    Reinbursement out = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      stmt = conn.prepareStatement("SELECT * FROM reinbursements WHERE id = ?"); // ? is index 1
      stmt.setInt(1, id);
      
      if (stmt.execute()) {
        
        rs = stmt.getResultSet();
      }
      
      while (rs.next()) {
      out = new Reinbursement(rs.getInt("id"), rs.getInt("emp_Id"), rs.getInt("manager_Id"),
          rs.getString("datetime"), rs.getString("type"), rs.getString("status"), rs.getDouble("cost"),
          rs.getString("requestor_comments"), rs.getString("approver_comments"), rs.getString("image_location"));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return out;
  }

  @Override
  public List<Reinbursement> getAll() {
    List<Reinbursement> allReinbursements = new ArrayList<Reinbursement>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
      stmt = conn.prepareStatement("Select * FROM reinbursements");
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allReinbursements.add(new Reinbursement(rs.getInt("id"), rs.getInt("emp_Id"), rs.getInt("manager_Id"),
            rs.getString("datetime"), rs.getString("type"), rs.getString("status"), rs.getDouble("cost"),
            rs.getString("requestor_comments"), rs.getString("approver_comments"), rs.getString("image_location")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return allReinbursements;
  }

  @Override
  public List<Reinbursement> getReinbursementsByEmployeeId(int employeeId) {
    List<Reinbursement> allReinbursementsWithEmployeeId = new ArrayList<Reinbursement>();
    PreparedStatement stmt = null;
    ResultSet rs = null;
    
    try {
      stmt = conn.prepareStatement("Select * FROM reinbursements where emp_Id = ?");
      stmt.setInt(1, employeeId);
      if (stmt.execute()) {
        rs = stmt.getResultSet();
      }
      while (rs.next()) {
        allReinbursementsWithEmployeeId.add(new Reinbursement(rs.getInt("id"), rs.getInt("emp_Id"), rs.getInt("manager_Id"),
            rs.getString("datetime"), rs.getString("type"), rs.getString("status"), rs.getDouble("cost"),
            rs.getString("requestor_comments"), rs.getString("approver_comments"), rs.getString("image_location")));
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    
    return allReinbursementsWithEmployeeId;
  }

  @Override
  public void save(Reinbursement reinbursement) {
    
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "INSERT INTO reinbursements (emp_Id, manager_Id, type, status, cost, "
          + "requestor_comments, approver_comments, image_location) "
          + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)");
      stmt.setInt(1, reinbursement.getEmp_Id());
      stmt.setInt(2, reinbursement.getManager_Id());
      stmt.setString(3, reinbursement.getType());
      stmt.setString(4, reinbursement.getStatus());
      stmt.setDouble(5, reinbursement.getCost());
      stmt.setString(6, reinbursement.getRequestor_comments());
      stmt.setString(7, reinbursement.getApprover_comments());
      stmt.setString(8, reinbursement.getImage_location());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }

  @Override
  public void update(Reinbursement reinbursement) {
    
    PreparedStatement stmt = null;

    try {
      stmt = conn.prepareStatement(
          "UPDATE reinbursements SET emp_Id = ?, manager_Id = ?, type = ?, status = ?,"
          + " cost = ?, requestor_comments = ?, approver_comments = ?, image_location = ? WHERE id = ?");
      stmt.setInt(1, reinbursement.getEmp_Id());
      stmt.setInt(2, reinbursement.getManager_Id());
      stmt.setString(3, reinbursement.getType());
      stmt.setString(4, reinbursement.getStatus());
      stmt.setDouble(5, reinbursement.getCost());
      stmt.setString(6, reinbursement.getRequestor_comments());
      stmt.setString(7, reinbursement.getApprover_comments());
      stmt.setString(8, reinbursement.getImage_location());
      stmt.setInt(9, reinbursement.getId());

      stmt.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    
  }

}
