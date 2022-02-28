package com.products.reto_carvajal.repo.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.reto_carvajal.model.Product;

public interface ProductCrudRepository extends JpaRepository<Product, Long> {

}
