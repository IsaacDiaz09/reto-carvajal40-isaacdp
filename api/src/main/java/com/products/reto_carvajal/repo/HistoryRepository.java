package com.products.reto_carvajal.repo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.products.reto_carvajal.model.History;
import com.products.reto_carvajal.repo.crud.HistoryCrudRepository;

@Repository
public class HistoryRepository {

	@Autowired
	private HistoryCrudRepository crudRepo;

	public Optional<History> getById(Long idHistory) {
		return crudRepo.findById(idHistory);
	}

	public Page<History> getAll(Pageable pageable) {
		return crudRepo.findAll(pageable);
	}

	public void save(History history) {
		crudRepo.save(history);
	}

	public void delete(Long idHistory) {
		crudRepo.deleteById(idHistory);
	}

	public void deleteAll() {
		crudRepo.deleteAll();
	}
}
