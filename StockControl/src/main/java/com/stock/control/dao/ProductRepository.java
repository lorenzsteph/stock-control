package com.stock.control.dao;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
