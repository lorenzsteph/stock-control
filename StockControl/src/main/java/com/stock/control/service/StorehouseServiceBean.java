package com.stock.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.StorehouseRepository;
import com.stock.control.dao.dynamic.command.StorehouseQueryCommand;
import com.stock.control.dao.dynamic.filter.StorehouseSearchFilter;
import com.stock.control.model.Storehouse;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class StorehouseServiceBean implements StorehouseService {

	@Autowired
	private StorehouseRepository storehouseRepository;

	@Override
	public Page<Storehouse> findStorehouseFilter(StorehouseSearchFilter storehouseFilter, Integer pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(storehouseFilter.getOrder()));
		StorehouseQueryCommand command = new StorehouseQueryCommand();

		return storehouseRepository.findAll(command.where(storehouseFilter), request);
	}

}
