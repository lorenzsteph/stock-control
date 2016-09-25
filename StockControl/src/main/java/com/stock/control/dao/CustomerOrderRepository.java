package com.stock.control.dao;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.CustomerOrder;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {

}
