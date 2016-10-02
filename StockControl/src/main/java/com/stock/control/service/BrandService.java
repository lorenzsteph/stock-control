package com.stock.control.service;

import java.util.List;

import com.stock.control.model.Brand;

public interface BrandService {

	List<Brand> findBrandByIdStockist(long idStockist);

}
