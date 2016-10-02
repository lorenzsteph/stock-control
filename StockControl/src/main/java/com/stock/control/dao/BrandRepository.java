package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long> {

	@Query("SELECT s FROM Brand s WHERE s.descr = :descr")
	public Brand findByDescr(@Param("descr") String descr);

	@Query("SELECT l FROM Brand l JOIN FETCH l.stockist s WHERE s.idStockist = :idStockist")
	public List<Brand> findBrandByIdStockist(@Param("idStockist") long idStockist);

}
