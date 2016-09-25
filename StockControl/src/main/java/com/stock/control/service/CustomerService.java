package com.stock.control.service;

import java.util.List;

import com.stock.control.model.Customer;

public interface CustomerService {

	public Customer saveOrUpdateCustomer(Customer customer);

	public void deleteCustomer(int idCustomer);

	public Customer getCustomer(int idCustomer);

	public List<Customer> getAllCustomer();

}
