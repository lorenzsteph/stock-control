package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.LinkProductRange;

public interface LinkProductRangeRepository extends CrudRepository<LinkProductRange, Long> {

	@Query("SELECT l FROM LinkProductRange l JOIN FETCH l.product s WHERE s.idProduct = :idProduct")
	public List<LinkProductRange> findRangeByIdProduct(@Param("idProduct") long idProduct);

}
