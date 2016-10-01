package com.stock.control.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Stockist;

public interface StockistRepository extends CrudRepository<Stockist, Long>, JpaSpecificationExecutor<Stockist> {

	@Query("SELECT s FROM Stockist s WHERE s.descr = :descr")
	Stockist findByDescr(@Param("descr") String descr);

}
