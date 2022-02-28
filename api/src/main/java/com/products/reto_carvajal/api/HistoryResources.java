package com.products.reto_carvajal.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.products.reto_carvajal.model.History;
import com.products.reto_carvajal.service.HistoryServiceImpl;

@RestController
@RequestMapping("/api/history")
public class HistoryResources {

	@Autowired
	private HistoryServiceImpl service;

	private final Sort sortByID = Sort.by(Direction.ASC, "idHistory");

	@GetMapping
	public Page<History> getHistory(@RequestParam(defaultValue = "0") Integer page,
			@RequestParam(defaultValue = "9") Integer size) {
		return service.getAll(PageRequest.of(page, size, sortByID));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void createHistory(@RequestBody History history) {
		service.save(history);
	}

	@DeleteMapping("/all")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAll() {
		service.deleteAll();
	}

}
