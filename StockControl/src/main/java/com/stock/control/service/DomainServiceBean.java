package com.stock.control.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.stock.control.dao.ClienteDao;
import com.stock.control.dao.FornitoreDao;
import com.stock.control.model.Cliente;
import com.stock.control.model.Fornitore;

@Service
@Transactional(value = "stockTransactionManager", propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = java.lang.Exception.class)
public class DomainServiceBean implements DomainService {

	private static final Logger log = LoggerFactory.getLogger(DomainServiceBean.class);

	@Autowired
	private ClienteDao clienteDao;

	@Autowired
	private FornitoreDao fornitoreDao;

	@Override
	public int saveOrUpdateFornitore(Fornitore fornitore) {
		return fornitoreDao.saveOrUpdate(fornitore);
	}

	@Override
	public int deleteFornitore(int idFornitore) {
		return fornitoreDao.delete(idFornitore);
	}

	@Override
	public Fornitore getFornitore(int idFornitore) {
		return fornitoreDao.getFornitore(idFornitore);
	}

	@Override
	public List<Fornitore> getAllFornitore() {
		return fornitoreDao.getAllFornitore();
	}

	public int saveOrUpdateCliente(Cliente cliente) {
		return clienteDao.saveOrUpdate(cliente);
	}

	public int deleteCliente(int idCliente) {
		return clienteDao.delete(idCliente);
	}

	public Cliente getCliente(int idCliente) {
		return clienteDao.getCliente(idCliente);
	}

	public List<Cliente> getAllCliente() {
		return clienteDao.getAllCliente();
	}

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
