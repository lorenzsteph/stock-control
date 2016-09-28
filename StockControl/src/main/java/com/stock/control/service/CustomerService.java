package com.stock.control.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.CustomerSearchFilter;
import com.stock.control.model.Customer;

public interface CustomerService {

	public Customer saveOrUpdateCustomer(Customer customer);

	public void deleteCustomer(int idCustomer);

	public Customer getCustomer(int idCustomer);

	public List<Customer> getAllCustomer();

	public Page<Customer> findCustomerFilter(CustomerSearchFilter customerFilter, Integer pageNumber, int pageSize);

}
