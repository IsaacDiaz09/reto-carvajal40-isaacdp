package com.products.reto_carvajal.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.products.reto_carvajal.model.Product;
import com.products.reto_carvajal.response.HttpResponse;
import com.products.reto_carvajal.service.ProductServiceImpl;

@RestController
@RequestMapping("/api")
public class ProductResources {

	@Autowired
	private ProductServiceImpl service;

	private final Sort sortByName = Sort.by(Direction.ASC, "name");

	@GetMapping("/product/{idProduct}")
	public Optional<Product> getProduct(@PathVariable("idProduct") Long idProduct) {
		return service.get(idProduct);
	}

	@GetMapping("/products")
	public Page<Product> getProducts(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "9") Integer size) {
		return service.getAll(PageRequest.of(page, size, sortByName));
	}

	// dev-only
	@PostMapping("/products")
	@ResponseStatus(HttpStatus.CREATED)
	public HttpResponse createProduct(@RequestBody List<Product> product) {
		return service.createProducts(product);
	}

	@PutMapping("/product")
	@ResponseStatus(HttpStatus.CREATED)
	public HttpResponse resetStock(@RequestBody Product product) {
		return service.resetStock(product);
	}
}
