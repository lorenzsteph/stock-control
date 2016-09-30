package com.stock.control.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StockistSearchFilter;
import com.stock.control.model.Stockist;

public interface StockistService {

	Stockist saveOrUpdateStockist(Stockist stockist);

	void deleteStockist(int idStockist);

	Stockist getStockist(int idStockist);

	List<Stockist> getAllStockist();

	Page<Stockist> findStockistFilter(StockistSearchFilter stockistFilter, Integer pageNumber, int pageSize);

}
