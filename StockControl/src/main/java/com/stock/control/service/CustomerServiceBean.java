package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.CustomerRepository;
import com.stock.control.model.Customer;

@Service
@Transactional(value = "transactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CustomerServiceBean implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer saveOrUpdateCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public void deleteCustomer(int idCustomer) {
		customerRepository.delete(Long.valueOf(idCustomer));
	}

	@Override
	public Customer getCustomer(int idCustomer) {
		return customerRepository.findOne(Long.valueOf(idCustomer));
	}

	@Override
	public List<Customer> getAllCustomer() {
		return (List<Customer>) customerRepository.findAll();
	}

}
