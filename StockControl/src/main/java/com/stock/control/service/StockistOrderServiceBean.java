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

import com.stock.control.dao.StockistOrderRepository;
import com.stock.control.dao.dynamic.command.StockistOrderQueryCommand;
import com.stock.control.dao.dynamic.filter.StockistOrderSearchFilter;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.Stockist;
import com.stock.control.model.StockistOrder;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class StockistOrderServiceBean implements StockistOrderService {

	@Autowired
	private StockistOrderRepository stockistOrderRepository;

	@Override
	public Page<StockistOrder> findStockistOrderFilter(StockistOrderSearchFilter stockistOrderFilter, int pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(stockistOrderFilter.getOrder()));
		StockistOrderQueryCommand command = new StockistOrderQueryCommand();

		return stockistOrderRepository.findAll(command.where(stockistOrderFilter), request);
	}

	@Override
	public void removeStockistOrder(StockistOrder selectedStockistOrder) {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrder(List<ProductOrder> cart, StockistOrder stockistOrder, Stockist selectedStockist) {
		// TODO Auto-generated method stub
		
	}

}
