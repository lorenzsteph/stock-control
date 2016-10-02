package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.CategoryRepository;
import com.stock.control.model.Category;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CategoryServiceBean implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findCategoryByIdBrand(long idBrand) {
		return categoryRepository.findCategoryByIdBrand(idBrand);
	}

}
