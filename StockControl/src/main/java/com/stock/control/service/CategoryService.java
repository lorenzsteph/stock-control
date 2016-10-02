package com.stock.control.service;

import java.util.List;

import com.stock.control.model.Category;

public interface CategoryService {

	List<Category> findCategoryByIdBrand(long idBrand);

}
