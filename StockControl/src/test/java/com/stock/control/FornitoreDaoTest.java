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
import com.stock.control.dao.FornitoreDao;
import com.stock.control.model.Fornitore;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StockConfiguration.class)
@WebAppConfiguration
public class FornitoreDaoTest {

	private static final Logger log = LoggerFactory.getLogger(FornitoreDaoTest.class);

	@Autowired
	private FornitoreDao fornitoreDao;

	@Test
	public void saveOrUpdateTest() throws Exception {
		log.debug("start test saveOrUpdateTest");
		Fornitore fornitore = new Fornitore();
		fornitore.setDescrizione("aaaaaaa");
		fornitoreDao.saveOrUpdate(fornitore);
	}

}
