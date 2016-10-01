package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.LinkProductRangeRepository;
import com.stock.control.model.LinkProductRange;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class RangeServiceBean implements RangeService {

	@Autowired
	private LinkProductRangeRepository linkProductRangeRepository;

	@Override
	public List<LinkProductRange> findRangeByIdProduct(long idProduct) {
		return linkProductRangeRepository.findRangeByIdProduct(idProduct);
	}

}
