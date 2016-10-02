package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	@Query("SELECT s FROM Category s WHERE s.descr = :descr")
	public Category findByDescr(@Param("descr") String descr);

	@Query("SELECT distinct l FROM Category l JOIN l.product p JOIN p.brand b WHERE b.idBrand = :idBrand")
	public List<Category> findCategoryByIdBrand(@Param("idBrand") long idBrand);

}
