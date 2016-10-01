package com.stock.control.service;

import java.util.List;

import com.stock.control.model.LinkStockistBrand;

public interface BrandService {

	List<LinkStockistBrand> findBrandByIdStockist(long idStockist);

}
