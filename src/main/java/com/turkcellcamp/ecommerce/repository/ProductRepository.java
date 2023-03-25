package com.turkcellcamp.ecommerce.repository;

import com.turkcellcamp.ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
