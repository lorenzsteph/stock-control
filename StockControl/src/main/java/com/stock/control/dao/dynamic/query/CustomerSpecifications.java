package com.stock.control.dao.dynamic.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.Customer;
import com.stock.control.model.Customer_;

public class CustomerSpecifications {

	private static Logger logger = LoggerFactory.getLogger(CustomerSpecifications.class);

	public static Specification<Customer> descr(final String pattern) {
		logger.debug("Customer: description");
		Specification<Customer> spec = new Specification<Customer>() {
			@Override
			public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(cb.lower(root.get(Customer_.descr)), pattern);
			}
		};
		return spec;
	}

}