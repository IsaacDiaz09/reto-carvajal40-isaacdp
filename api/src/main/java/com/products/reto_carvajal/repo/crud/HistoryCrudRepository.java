package com.products.reto_carvajal.repo.crud;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.reto_carvajal.model.History;

public interface HistoryCrudRepository extends JpaRepository<History, Long> {

}
