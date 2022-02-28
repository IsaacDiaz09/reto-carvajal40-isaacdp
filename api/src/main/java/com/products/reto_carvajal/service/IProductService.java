package com.products.reto_carvajal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.products.reto_carvajal.model.Product;
import com.products.reto_carvajal.response.HttpResponse;

public interface IProductService {

	public Optional<Product> get(Long idProduct);

	public Page<Product> getAll(Pageable pageable);

	public HttpResponse createProducts(List<Product> product);

}
