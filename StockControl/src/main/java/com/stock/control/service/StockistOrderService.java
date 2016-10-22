package com.stock.control.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StockistOrderSearchFilter;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.Stockist;
import com.stock.control.model.StockistOrder;

public interface StockistOrderService {

	public Page<StockistOrder> findStockistOrderFilter(StockistOrderSearchFilter stockistOrderFilter, int pageNumber, int pageSize);

	public void saveOrder(List<ProductOrder> cart, StockistOrder stockistOrder, Stockist selectedStockist);

}
