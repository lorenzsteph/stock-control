package com.stock.control.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.stock.control.model.Customer;
import com.stock.control.service.CustomerService;

@RestController
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	public CustomerService domainService;

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public ResponseEntity<List<Customer>> listAllClienti() {
		log.debug("Domain controller listAllClienti");
		List<Customer> clienti = domainService.getAllCustomer();

		log.debug("listAllClienti size : " + clienti.size());
		if (clienti.isEmpty()) {
			return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Customer>>(clienti, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int id) {
		log.debug("Fetching & Deleting Customer with id " + id);
		Customer customer = domainService.getCustomer(id);
		if (customer == null) {
			log.debug("Unable to delete. Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		domainService.deleteCustomer(id);
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> getUser(@PathVariable("id") int id) {
		log.debug("Fetching User with id " + id);
		Customer customer = domainService.getCustomer(id);
		if (customer == null) {
			log.debug("customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@RequestMapping(value = "/customer/", method = RequestMethod.POST)
	public ResponseEntity<Void> createUser(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		log.debug("Creating customer : " + customer.getDescr());

		domainService.saveOrUpdateCustomer(customer);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/customer/{id}").buildAndExpand(customer.getIdCustomer()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Customer> updateUser(@PathVariable("id") int id, @RequestBody Customer customer) {
		log.debug("Updating customer " + id);

		Customer currentCustomer = domainService.getCustomer(id);

		if (currentCustomer == null) {
			log.debug("Customer with id " + id + " not found");
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}

		currentCustomer.setDescr(customer.getDescr());
		currentCustomer.setDateEndValidity(customer.getDateEndValidity());

		domainService.saveOrUpdateCustomer(currentCustomer);
		return new ResponseEntity<Customer>(currentCustomer, HttpStatus.OK);
	}

}