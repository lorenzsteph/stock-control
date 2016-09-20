package com.stock.control.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.ClienteDao;
import com.stock.control.model.Cliente;

@Service
@Transactional(value = "stockTransactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class DomainServiceBean implements DomainService {

	private static final Logger log = LoggerFactory.getLogger(DomainServiceBean.class);

	@Autowired
	private ClienteDao clienteDao;

	@Override
	public Cliente populateDummyCliente(boolean exception) throws Exception {
		log.debug("populateDummyCliente start");
		Cliente c = new Cliente();
		c.setDescrizione("testRollback");
		clienteDao.saveOrUpdate(c);

		if (exception) {
			throw new Exception("provo a fare rollback");
		}

		return c;
	}

}
