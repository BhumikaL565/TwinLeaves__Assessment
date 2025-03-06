package com.bhumika.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bhumika.entity.Batch;
import com.bhumika.entity.Gtin;
import com.bhumika.entity.Product;
import com.bhumika.repository.BatchRepo;
import com.bhumika.repository.GtinRepo;
import com.bhumika.repository.ProductRepo;
import com.bhumika.service.DataServices;


@RestController
@RequestMapping("/api/")
public class DataController {

    @Autowired
    private DataServices dataService;

   
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product createdProduct = dataService.createProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

   
    @PostMapping("/gtins")
    public ResponseEntity<Gtin> createGtin(@RequestBody Gtin gtin) {
        Gtin createdGtin = dataService.createGtin(gtin);
        return new ResponseEntity<>(createdGtin, HttpStatus.CREATED);
    }

    
    @PostMapping("/batches")
    public ResponseEntity<Batch> createBatch(@RequestBody Batch batch) {
        Batch createdBatch = dataService.createBatch(batch);
        return new ResponseEntity<>(createdBatch, HttpStatus.CREATED);
    }

   
    @GetMapping("/gtin/{id}")
    public ResponseEntity<Gtin> getGtinById(@PathVariable Integer id) {
        return dataService.getGtinById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //@return A list of all GTIN objects.
    @GetMapping("/gtins")
    public ResponseEntity<List<Gtin>> getAllGtins() {
        List<Gtin> gtins = dataService.getAllGtins();
        return ResponseEntity.ok(gtins);
    }

   
     // @return A list of GTIN objects with the specified GTIN entity.
    @GetMapping("/gtins/{gtin}")
    public ResponseEntity<List<Gtin>> getGtinsByGtinEntity(@RequestParam String gtin) {
        List<Gtin> gtins = dataService.getGtinByGtin(gtin);
        return ResponseEntity.ok(gtins);
    }

    // @return A list of GTIN objects that have positive available quantity batches.
    @GetMapping("/gtins/positive-quantity")
    public ResponseEntity<List<Gtin>> getGtinsWithPositiveQuantityBatches() {
        List<Gtin> gtins = dataService.getGtinsWithPositiveQuantityBatches();
        return ResponseEntity.ok(gtins);
    }

   // @return The latest batch with negative or zero available quantity, or an HTTP
   // status code of 404 (Not Found) if not found.
    @GetMapping("/batches/negative-zero-latest")
    public ResponseEntity<Batch> getLatestBatchWithNegativeOrZeroQuantity() {
        Batch batch = dataService.getLatestBatchWithNegativeOrZeroQuantity();
        if (batch != null) {
            return ResponseEntity.ok(batch);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}