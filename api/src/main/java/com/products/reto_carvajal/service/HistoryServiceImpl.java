package com.products.reto_carvajal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.products.reto_carvajal.model.History;
import com.products.reto_carvajal.repo.HistoryRepository;

@Service
public class HistoryServiceImpl implements IHistoryService {

	@Autowired
	private HistoryRepository repo;

	@Override
	public void save(History history) {
		repo.save(history);
	}

	@Override
	public Page<History> getAll(Pageable pageable) {
		return repo.getAll(pageable);
	}

	@Override
	public void deleteAll() {
		repo.deleteAll();
	}

}
