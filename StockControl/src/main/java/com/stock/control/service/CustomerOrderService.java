package com.stock.control.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.model.Customer;
import com.stock.control.model.CustomerOrder;
import com.stock.control.model.ProductOrder;

public interface CustomerOrderService {

	public Page<CustomerOrder> findCustomerOrderFilter(CustomerOrderSearchFilter customerOrderFilter, int pageNumber, int pageSize);

	public void saveOrder(List<ProductOrder> cart, CustomerOrder customerOrder, Customer selectedCustomer);

}
