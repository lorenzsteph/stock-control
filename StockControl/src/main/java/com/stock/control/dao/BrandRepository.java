package com.stock.control.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Brand;

public interface BrandRepository extends CrudRepository<Brand, Long> {

	@Query("SELECT s FROM Brand s WHERE s.descr = :descr")
	Brand findByDescr(@Param("descr") String descr);

}
