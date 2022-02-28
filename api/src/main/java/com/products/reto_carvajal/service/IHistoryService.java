package com.products.reto_carvajal.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.products.reto_carvajal.model.History;

public interface IHistoryService {

	public Page<History> getAll(Pageable pageable);

	public void save(History history);

	public void deleteAll();

}
