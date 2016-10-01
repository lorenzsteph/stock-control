package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.LinkStockistBrandRepository;
import com.stock.control.model.LinkStockistBrand;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class BrandServiceBean implements BrandService {

	@Autowired
	private LinkStockistBrandRepository linkStockistBrandRepository;

	@Override
	public List<LinkStockistBrand> findBrandByIdStockist(long idStockist) {
		return linkStockistBrandRepository.findBrandByIdStockist(idStockist);
	}

}
