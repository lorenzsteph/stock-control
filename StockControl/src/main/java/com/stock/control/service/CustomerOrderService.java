package com.stock.control.service;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.model.CustomerOrder;

public interface CustomerOrderService {

	public Page<CustomerOrder> findCustomerOrderFilter(CustomerOrderSearchFilter customerOrderFilter, int pageNumber, int pageSize);

}
