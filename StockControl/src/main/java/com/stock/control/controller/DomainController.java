package com.stock.control.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stock.control.model.Cliente;
import com.stock.control.model.Fornitore;
import com.stock.control.service.DomainService;

@RestController
public class DomainController {

	private static final Logger log = LoggerFactory.getLogger(DomainController.class);

	@Autowired
	public DomainService domainService;

	@RequestMapping(value = "/cliente/", method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listAllClienti() {
		log.debug("Domain controller listAllClienti");
		List<Cliente> clienti = domainService.getAllCliente();

		log.debug("listAllClienti size : " + clienti.size());
		if (clienti.isEmpty()) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cliente>>(clienti, HttpStatus.OK);
	}

	@RequestMapping(value = "/fornitore/", method = RequestMethod.GET)
	public ResponseEntity<List<Fornitore>> listAllFornitori() {
		log.debug("Domain controller listAllFornitori");
		List<Fornitore> fornitori = domainService.getAllFornitore();
		log.debug("listAllFornitori size : " + fornitori.size());

		if (fornitori.isEmpty()) {
			return new ResponseEntity<List<Fornitore>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Fornitore>>(fornitori, HttpStatus.OK);
	}
}