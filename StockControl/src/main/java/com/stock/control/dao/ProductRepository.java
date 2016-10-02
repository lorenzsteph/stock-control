package com.stock.control.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.stock.control.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, JpaSpecificationExecutor<Product> {

	@Query("SELECT s FROM Product s WHERE s.codProduct = :cod")
	Product findByCodProduct(@Param("cod") String cod);
}
