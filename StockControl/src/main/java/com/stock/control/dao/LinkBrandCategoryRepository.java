package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.LinkBrandCategory;

public interface LinkBrandCategoryRepository extends CrudRepository<LinkBrandCategory, Long> {

	@Query("SELECT l FROM LinkBrandCategory l JOIN FETCH l.brand s WHERE s.idBrand = :idBrand")
	public List<LinkBrandCategory> findCategoryByIdBrand(@Param("idBrand") long idBrand);

}
