package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.dao.dynamic.query.CustomerOrderSpecifications;
import com.stock.control.model.CustomerOrder;

public class CustomerOrderQueryCommand implements QueryCommand<CustomerOrderSearchFilter, CustomerOrder> {

	@Override
	public Specifications<CustomerOrder> where(CustomerOrderSearchFilter filter) {
		Specifications<CustomerOrder> spec = null;

		if (filter != null) {
			if (filter.getCustomer() != null) {
				spec = setSpecification(spec, CustomerOrderSpecifications.customer(filter.getCustomer()));
			}

			if (filter.getDateOrder() != null) {
				spec = setSpecification(spec, CustomerOrderSpecifications.dateOrder(filter.getDateOrder()));
			}
		}

		return spec;
	}

	private Specifications<CustomerOrder> setSpecification(Specifications<CustomerOrder> spec, Specification<CustomerOrder> specification) {
		if (spec == null) {
			spec = Specifications.where(specification);
		} else {
			spec.and(specification);
		}
		return spec;
	}
}