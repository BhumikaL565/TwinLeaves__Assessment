package com.bhumika.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bhumika.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

}
