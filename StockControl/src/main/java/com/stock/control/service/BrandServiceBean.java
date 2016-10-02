package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.BrandRepository;
import com.stock.control.model.Brand;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class BrandServiceBean implements BrandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public List<Brand> findBrandByIdStockist(long idStockist) {
		return brandRepository.findBrandByIdStockist(idStockist);
	}

}
