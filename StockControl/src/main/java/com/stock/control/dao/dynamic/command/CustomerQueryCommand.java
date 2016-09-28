package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.CustomerSearchFilter;
import com.stock.control.dao.dynamic.query.CustomerSpecifications;
import com.stock.control.model.Customer;

public class CustomerQueryCommand implements QueryCommand<CustomerSearchFilter, Customer> {

	@Override
	public Specifications<Customer> where(CustomerSearchFilter filter) {
		Specifications<Customer> spec = null;

		if (filter != null) {
			if (filter.getDescr() != null) {
				spec = Specifications.where(CustomerSpecifications.descr(filter.getDescr().toLowerCase()));
			}
		}

		return spec;
	}

}