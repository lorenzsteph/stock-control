package com.stock.control.dao.repository;

import org.springframework.data.repository.CrudRepository;

import com.stock.control.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
