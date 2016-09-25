package com.stock.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.dao.repository.CustomerRepository;
import com.stock.control.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = StockConfiguration.class)
@WebAppConfiguration
public class CustomerRepositoryTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Test
	public void saveOrUpdateTest() throws Exception {
		log.debug("start test saveOrUpdateTest");
		Customer customer = new Customer();
		customer.setDescr("test");
		customer.setIdCustomer(0L);
		customerRepository.save(customer);
	}

}
