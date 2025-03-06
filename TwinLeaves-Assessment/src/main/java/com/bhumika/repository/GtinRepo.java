package com.bhumika.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bhumika.entity.Gtin;

@Repository
public interface GtinRepo extends JpaRepository<Gtin, Integer>{ 
	
	//Finds GTINs that partially match the given {@code gtin} parameter.
	@Query("SELECT g FROM Gtin g WHERE LOWER(g.gtin) LIKE LOWER(CONCAT('%', :gtin, '%'))")
    List<Gtin> findGtinsByGtin(String gtin);

}
