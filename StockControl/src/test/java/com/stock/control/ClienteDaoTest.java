package com.stock.control;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.stock.control.configuration.StockConfiguration;
import com.stock.control.dao.ClienteDao;
import com.stock.control.model.Cliente;
import com.stock.control.service.DomainService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = StockConfiguration.class)
@WebAppConfiguration
public class ClienteDaoTest {

	private static final Logger log = LoggerFactory.getLogger(ClienteDaoTest.class);

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private DomainService domainService;

	@Test
	public void saveOrUpdateTest() throws Exception {
		log.debug("start test saveOrUpdateTest");
		Cliente cliente = new Cliente();
		cliente.setDescrizione("aaaaaaa");
		clienteDao.saveOrUpdate(cliente);
	}

	@Ignore
	@Test
	public void domainServiceRollback() throws Exception {
		log.debug("start test domainServiceRollback");
		List<Cliente> listCliente = clienteDao.getAllCliente();
		log.debug("cliente size : " + listCliente.size());
		boolean exception = false;
		try {
			domainService.testRollbackCliente(true);
		} catch (Exception e) {
			exception = true;
		}

		Assert.assertTrue(exception);
		List<Cliente> listCliente2 = clienteDao.getAllCliente();
		Assert.assertEquals(listCliente.size(), listCliente2.size());
	}

}
