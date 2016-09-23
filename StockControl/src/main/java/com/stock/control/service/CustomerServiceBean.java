package com.stock.control.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.CustomerDao;
import com.stock.control.model.Customer;

@Service
@Transactional(value = "stockTransactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class CustomerServiceBean implements CustomerService {

	@Autowired
	private CustomerDao customerDao;

	@Override
	public int saveOrUpdateCustomer(Customer customer) {
		return customerDao.saveOrUpdate(customer);
	}

	@Override
	public int deleteCustomer(int idCustomer) {
		return customerDao.delete(idCustomer);
	}

	@Override
	public Customer getCustomer(int idCustomer) {
		return customerDao.getCustomer(idCustomer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerDao.getAllCustomer();
	}

}
