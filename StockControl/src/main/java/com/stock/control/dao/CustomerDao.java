package com.stock.control.dao;

import java.util.List;

import com.stock.control.model.Customer;

public interface CustomerDao {

	public int saveOrUpdate(Customer customer);

	public int delete(int idCustomer);

	public Customer getCustomer(int idCustomer);

	public List<Customer> getAllCustomer();
}
