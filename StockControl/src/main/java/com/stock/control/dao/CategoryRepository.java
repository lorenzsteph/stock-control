package com.stock.control.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	@Query("SELECT s FROM Category s WHERE s.descr = :descr")
	Category findByDescr(@Param("descr") String descr);
}
