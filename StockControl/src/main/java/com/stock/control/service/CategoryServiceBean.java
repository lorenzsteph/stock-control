package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.LinkBrandCategoryRepository;
import com.stock.control.model.LinkBrandCategory;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CategoryServiceBean implements CategoryService {

	@Autowired
	private LinkBrandCategoryRepository linkBrandCategoryRepository;

	@Override
	public List<LinkBrandCategory> findCategoryByIdBrand(long idBrand) {
		return linkBrandCategoryRepository.findCategoryByIdBrand(idBrand);
	}

}
