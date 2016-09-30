package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.StockistRepository;
import com.stock.control.dao.dynamic.command.StockistQueryCommand;
import com.stock.control.dao.dynamic.filter.StockistSearchFilter;
import com.stock.control.model.Stockist;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class StockistServiceBean implements StockistService {

	@Autowired
	private StockistRepository stockistRepository;

	@Override
	public Stockist saveOrUpdateStockist(Stockist stockist) {
		return stockistRepository.save(stockist);
	}

	@Override
	public void deleteStockist(int idStockist) {
		stockistRepository.delete(Long.valueOf(idStockist));
	}

	@Override
	public Stockist getStockist(int idStockist) {
		return stockistRepository.findOne(Long.valueOf(idStockist));
	}

	@Override
	public List<Stockist> getAllStockist() {
		return (List<Stockist>) stockistRepository.findAll();
	}

	@Override
	public Page<Stockist> findStockistFilter(StockistSearchFilter stockistFilter, Integer pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(stockistFilter.getOrder()));
		StockistQueryCommand command = new StockistQueryCommand();

		return stockistRepository.findAll(command.where(stockistFilter), request);
	}

}
