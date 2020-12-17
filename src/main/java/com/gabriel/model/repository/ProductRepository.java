package com.gabriel.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
