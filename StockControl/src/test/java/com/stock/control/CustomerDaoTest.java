package com.stock.control;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.configuration.StockConfiguration;
import com.stock.control.dao.CustomerDao;
import com.stock.control.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StockConfiguration.class)
@WebAppConfiguration
public class CustomerDaoTest {

	private static final Logger log = LoggerFactory.getLogger(CustomerDaoTest.class);

	@Autowired
	private CustomerDao clienteDao;

	@Test
	public void saveOrUpdateTest() throws Exception {
		log.debug("start test saveOrUpdateTest");
		Customer c = new Customer();
		c.setDescr("aaaaaaa");
		clienteDao.saveOrUpdate(c);
	}

}
