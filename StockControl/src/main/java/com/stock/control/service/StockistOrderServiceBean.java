package com.stock.control.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.StockistOrderProductRepository;
import com.stock.control.dao.StockistOrderRepository;
import com.stock.control.dao.dynamic.command.StockistOrderQueryCommand;
import com.stock.control.dao.dynamic.filter.StockistOrderSearchFilter;
import com.stock.control.model.ProductOrder;
import com.stock.control.model.Stockist;
import com.stock.control.model.StockistOrder;
import com.stock.control.model.StockistOrderProduct;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class StockistOrderServiceBean implements StockistOrderService {

	private static final Logger log = LoggerFactory.getLogger(StockistOrderServiceBean.class);

	@Autowired
	private StockistOrderRepository stockistOrderRepository;

	@Autowired
	private StockistOrderProductRepository stockistOrderProductRepository;

	@Override
	public Page<StockistOrder> findStockistOrderFilter(StockistOrderSearchFilter stockistOrderFilter, int pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(stockistOrderFilter.getOrder()));
		StockistOrderQueryCommand command = new StockistOrderQueryCommand();

		return stockistOrderRepository.findAll(command.where(stockistOrderFilter), request);
	}

	@Override
	public void saveOrder(List<ProductOrder> cart, StockistOrder stockistOrder, Stockist selectedStockist) {
		stockistOrder.setStockist(selectedStockist);
		log.debug("save stockist order in service");

		stockistOrder = stockistOrderRepository.save(stockistOrder);
		log.debug("stockistr order id : " + stockistOrder.getIdStockistOrder());

		for (ProductOrder productOrder : cart) {

			for (int i = 0; productOrder.getAmount() > i; i++) {

				StockistOrderProduct sop = new StockistOrderProduct();

				sop.setStockistOrder(stockistOrder);
				sop.setProduct(productOrder.getProduct());
				sop.setPrice(productOrder.getPrice());
				sop.setDescr("none");

				stockistOrderProductRepository.save(sop);

			}
		}

	}

}
