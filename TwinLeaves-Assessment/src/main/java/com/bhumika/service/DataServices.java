package com.bhumika.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bhumika.entity.Batch;
import com.bhumika.entity.Gtin;
import com.bhumika.entity.Product;
import com.bhumika.repository.BatchRepo;
import com.bhumika.repository.GtinRepo;
import com.bhumika.repository.ProductRepo;

@Service
public class DataServices {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private GtinRepo gtinRepo;

    @Autowired
    private BatchRepo batchRepo;

   
    public Product createProduct(Product product) {
        return productRepo.save(product);
    }

   
    public Gtin createGtin(Gtin gtin) {
        return gtinRepo.save(gtin);
    }

    
    public Batch createBatch(Batch batch) {
        return batchRepo.save(batch);
    }

    // Retrieves a GTIN by its ID.   
    // @return An Optional containing the GTIN if found, otherwise an empty Optional.  
    public Optional<Gtin> getGtinById(Integer id) {
        return gtinRepo.findById(id);
    }

   
     // Retrieves a list of GTINs by their GTIN value.
     //@return A list of GTINs that match the given GTIN value.
    public List<Gtin> getGtinByGtin(String gtin) {
        return gtinRepo.findGtinsByGtin(gtin);
    }

   
    //Retrieves all GTINs from the database.
    // @return A list of all GTINs.
    public List<Gtin> getAllGtins() {
        return gtinRepo.findAll();
    }

 
    //Retrieves a list of GTINs that have associated batches with positive available quantity.
    //@return A list of distinct GTINs with positive available quantity batches.
    public List<Gtin> getGtinsWithPositiveQuantityBatches() {
        List<Batch> batches = batchRepo.findPositiveAvailableQuantityBatches();
        return batches.stream()
                .map(Batch::getGtin)
                .distinct()
                .collect(Collectors.toList());
    }

   
    //Retrieves the latest batch with negative or zero available quantity.
    //@return The latest batch with negative or zero available quantity, or null if no such batch exists.
    public Batch getLatestBatchWithNegativeOrZeroQuantity() {
        List<Batch> batches = batchRepo.findNegativeOrZeroAvailableQuantityBatches();
        return batches.isEmpty() ? null : batches.get(0);
    }
}
