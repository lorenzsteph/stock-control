package com.stock.control.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Range;

public interface RangeRepository extends CrudRepository<Range, Long> {

	@Query("SELECT s FROM Range s WHERE s.descr = :descr")
	Range findByDescr(@Param("descr") String descr);
}
