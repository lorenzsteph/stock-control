package com.stock.control.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.CustomerOrderRepository;
import com.stock.control.dao.dynamic.command.CustomerOrderQueryCommand;
import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.model.CustomerOrder;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CustomerOrderServiceBean implements CustomerOrderService {

	@Autowired
	private CustomerOrderRepository customerOrderRepository;

	@Override
	public Page<CustomerOrder> findCustomerOrderFilter(CustomerOrderSearchFilter customerOrderFilter, int pageNumber, int pageSize) {
		Pageable request = new PageRequest(pageNumber, pageSize, new Sort(customerOrderFilter.getOrder()));
		CustomerOrderQueryCommand command = new CustomerOrderQueryCommand();

		return customerOrderRepository.findAll(command.where(customerOrderFilter), request);
	}

}
