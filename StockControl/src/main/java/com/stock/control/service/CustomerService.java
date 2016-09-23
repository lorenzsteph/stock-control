package com.stock.control.service;

import java.util.List;

import com.stock.control.model.Customer;

public interface CustomerService {

	public int saveOrUpdateCustomer(Customer customer);

	public int deleteCustomer(int idCustomer);

	public Customer getCustomer(int idCustomer);

	public List<Customer> getAllCustomer();

}
