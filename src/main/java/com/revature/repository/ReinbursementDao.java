package com.revature.repository;

import java.util.List;
import com.revature.model.Reinbursement;

public interface ReinbursementDao {

  /**
   * Get a Reinbursement by its id
   * @param id
   * @return
   */
    Reinbursement get(int id);
    
    /**
     * Gets all Reinbursement
     * @ return
     */
    List<Reinbursement> getAll();
    
    /**
     * Gets Reinbursement by the Employee Id
     * @return
     */
    List<Reinbursement> getReinbursementsByEmployeeId(int employeeId);
    
    /**
     * Save a new Reinbursement
     * @param  A Reinbursement not persisted yet
     */
    void save(Reinbursement reinbursement);
    
    /**
     * Update an existing Reinbursement using its id.
     * @param Reinbursement
     */
    void update(Reinbursement reinbursement);
  
}
