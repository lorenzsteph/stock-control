package com.stock.control.dao;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

}
