package com.products.reto_carvajal.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;

import com.products.reto_carvajal.model.Product;
import com.products.reto_carvajal.response.HttpResponse;

/**
 * Pruebas del servicio de productos Nota: se realizaron con la base de datos
 * vacia
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductServiceImplTest {

	@Autowired
	private ProductServiceImpl service;

	private static final String PRODUCT_NAME = "test";
	private static final String PRODUCT_DESCRIPTION = "description";
	private static final int PRODUCT_STOCK = 1;
	private static final Boolean PRODUCT_AVAIALBILITY = true;
	private static final String PRODUCT_IMG = "https://www.istockphoto.com/es/foto/coche-suv-moderno-gen%C3%A9rico-en-garaje-de-hormig%C3%B3n-gm1307086567-397470049";
	private static final Double PRODUCT_PRICE = 20D;
	private static final int FIRST_PAGE = 0;
	private static final int NO_ELEMENTS = 5;
	private static final Sort sortByName = Sort.by(Direction.ASC, "name");
	private static final int NO_STOCK = 0;
	private static final Boolean NO_AVAILABLE = false;

	@Test
	@Order(1)
	public void getAllProducts_ShouldReturnPageProductsResponse() {
		Product product = new Product(null, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_STOCK, PRODUCT_PRICE,
				PRODUCT_AVAIALBILITY, PRODUCT_IMG);

		List<Product> products = Arrays.asList(product, product, product, product, product);
		service.createProducts(products);

		Page<Product> productsResponse = service.getAll(PageRequest.of(FIRST_PAGE, NO_ELEMENTS, sortByName));

		products = productsResponse.getContent();
		boolean isPaged = productsResponse.getPageable().isPaged();
		boolean isSorted = productsResponse.getSort().isSorted();
		int page = productsResponse.getPageable().getPageNumber();

		Assertions.assertEquals(products.size(), NO_ELEMENTS);
		Assertions.assertEquals(page, FIRST_PAGE);
		Assertions.assertTrue(isPaged);
		Assertions.assertTrue(isSorted);
	}

	@Test
	@Order(2)
	public void productStock_ShouldBeReset() {
		Product product = service.get(1L).get();

		HttpResponse response = service.resetStock(product);

		Product prodReseted = service.get(1L).get();

		Assertions.assertEquals(response.getStatusCode(), HttpStatus.CREATED.value());
		Assertions.assertEquals(response.getMessage(), "Operacion exitosa");
		// NO_AVAILABLE = false, NO_STOCK = 0
		Assertions.assertEquals(prodReseted.getIsAvailable(), NO_AVAILABLE);
		Assertions.assertEquals(prodReseted.getStock(), NO_STOCK);
	}

}
