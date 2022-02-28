package com.products.reto_carvajal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.products.reto_carvajal.repo.crud.HistoryCrudRepository;
import com.products.reto_carvajal.repo.crud.ProductCrudRepository;

/**
 * @author: Isaac Diaz Perez
 */
@SpringBootApplication
public class RetoCarvajalApplication implements CommandLineRunner {

	@Autowired
	private HistoryCrudRepository historyRepo;
	@Autowired
	private ProductCrudRepository productRepo;

	public static void main(String[] args) {
		SpringApplication.run(RetoCarvajalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(String.format("Hay %d registros de historial de productos.", historyRepo.count()));
		System.out.println(String.format("Hay %d productos en el sistema.", productRepo.count()));
	}

}
