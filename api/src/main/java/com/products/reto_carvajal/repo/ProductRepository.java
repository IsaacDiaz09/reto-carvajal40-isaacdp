package com.products.reto_carvajal.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.products.reto_carvajal.model.Product;
import com.products.reto_carvajal.repo.crud.ProductCrudRepository;

@Repository
public class ProductRepository {

	@Autowired
	private ProductCrudRepository crudRepo;

	public Optional<Product> getById(Long idProduct) {
		return crudRepo.findById(idProduct);
	}

	public Page<Product> getAll(Pageable pageable) {
		return crudRepo.findAll(pageable);
	}

	public void save(Product product) {
		crudRepo.save(product);
	}
	
	public void delete(Long idProduct) {
		crudRepo.deleteById(idProduct);
	}
}
