package com.bhumika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bhumika.entity.Batch;


@Repository
public interface BatchRepo extends JpaRepository<Batch, Integer>{
	 
     //Retrieves a list of batches with a positive available quantity.
    @Query("SELECT b FROM Batch b WHERE b.availableQuantity > 0")
    List<Batch> findPositiveAvailableQuantityBatches();

    //Retrieves a list of batches with a non-positive available quantity, sorted by inward date in descending order.
    @Query(value = "SELECT * FROM batch_table b WHERE b.available_quantity <= 0 ORDER BY b.inwarded_on DESC", nativeQuery = true)
    List<Batch> findNegativeOrZeroAvailableQuantityBatches();

   

}
