package com.stock.control.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.LinkCategoryProduct;

public interface LinkCategoryProductRepository extends CrudRepository<LinkCategoryProduct, Long>, JpaSpecificationExecutor<LinkCategoryProduct> {

	@Query("SELECT l FROM LinkCategoryProduct l JOIN FETCH l.category s WHERE s.idCategory = :idCategory")
	public List<LinkCategoryProduct> findProductByIdCategory(@Param("idCategory") long idCategory);

}
