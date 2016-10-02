package com.stock.control.service;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StorehouseSearchFilter;
import com.stock.control.model.Storehouse;

public interface StorehouseService {

	public Page<Storehouse> findStorehouseFilter(StorehouseSearchFilter storehouseFilter, Integer pageNumber, int pageSize);

}
