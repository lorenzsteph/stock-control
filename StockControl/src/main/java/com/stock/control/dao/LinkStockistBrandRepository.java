package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.LinkStockistBrand;

public interface LinkStockistBrandRepository extends CrudRepository<LinkStockistBrand, Long> {

	@Query("SELECT l FROM LinkStockistBrand l JOIN FETCH l.stockist s WHERE s.idStockist = :idStockist")
	public List<LinkStockistBrand> findBrandByIdStockist(@Param("idStockist") long idStockist);
	
}
