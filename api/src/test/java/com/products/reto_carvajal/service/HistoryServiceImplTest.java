package com.products.reto_carvajal.service;

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

import com.products.reto_carvajal.model.History;
import com.products.reto_carvajal.model.Product;

/**
 * Pruebas del servicio de productos Nota: se realizaron con la base de datos
 * vacia
 */
@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HistoryServiceImplTest {

	@Autowired
	private HistoryServiceImpl service;
	@Autowired
	private ProductServiceImpl productService;

	private static final int FIRST_PAGE = 0;
	private static final int NO_ELEMENTS = 5;
	private static final int EMPTY = 0;

	@Test
	@Order(1)
	public void getAllHistoryProducts_ShouldReturnHistoryPageResponse() {
		Product product = productService.get(1L).get();

		History history = new History(product);
		History history1 = new History(product);
		History history2 = new History(product);
		History history3 = new History(product);
		History history4 = new History(product);

		service.save(history);
		service.save(history1);
		service.save(history2);
		service.save(history3);
		service.save(history4);

		Page<History> historyResponse = service.getAll(PageRequest.of(FIRST_PAGE, NO_ELEMENTS));

		List<History> content = historyResponse.getContent();
		boolean isPaged = historyResponse.getPageable().isPaged();
		boolean isSorted = historyResponse.getSort().isSorted();
		int page = historyResponse.getPageable().getPageNumber();

		// NO_ELEMENTS = 5, FIRST_PAGE= 1
		Assertions.assertEquals(content.size(), NO_ELEMENTS);
		Assertions.assertEquals(historyResponse.getTotalElements(), NO_ELEMENTS);
		Assertions.assertEquals(page, FIRST_PAGE);
		Assertions.assertTrue(isPaged);
		Assertions.assertFalse(isSorted);
	}

	@Test
	@Order(2)
	public void allHistoryProducts_ShouldBeDeleted() {
		service.deleteAll();

		Page<History> historyResponse = service.getAll(PageRequest.of(FIRST_PAGE, NO_ELEMENTS));
		List<History> content = historyResponse.getContent();
		Assertions.assertEquals(content.size(), EMPTY);
		Assertions.assertEquals(historyResponse.getTotalElements(), EMPTY);
	}

}
