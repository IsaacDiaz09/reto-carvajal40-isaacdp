package com.products.reto_carvajal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.products.reto_carvajal.exception.ApiBadRequestException;
import com.products.reto_carvajal.model.Product;
import com.products.reto_carvajal.repo.ProductRepository;
import com.products.reto_carvajal.response.HttpResponse;

@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private ProductRepository repo;

	@Override
	public Optional<Product> get(Long idProduct) {
		return repo.getById(idProduct);
	}

	@Override
	public Page<Product> getAll(Pageable pageable) {
		return repo.getAll(pageable);
	}

	// dev-only
	@Override
	public HttpResponse createProducts(List<Product> products) {
		int counter = 0;

		for (Product prod : products) {
			try {
				repo.save(new Product(null, prod.getName(), prod.getDescription(), prod.getStock(), prod.getPrice(),
						prod.getStock() > 0, prod.getImage()));
				counter++;
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}

		return HttpResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED,
				String.format("Se han creado %d products", counter));
	}

	// Este metodo es para probar la funcionalidad de alerta cuando el producto no
	// tiene stock, del lando del front-end
	public HttpResponse resetStock(Product product) {
		Optional<Product> existProduct = repo.getById(product.getIdProd());

		if (existProduct.isPresent()) {
			product = existProduct.get();
			product.setStock(0);
			product.setIsAvailable(false);

			repo.save(product);
		} else {
			throw new ApiBadRequestException("El producto no existe!");
		}

		return HttpResponse.of(201, HttpStatus.CREATED, "Operacion exitosa");
	}

}
