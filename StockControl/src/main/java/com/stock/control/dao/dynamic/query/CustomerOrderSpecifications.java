package com.stock.control.dao.dynamic.query;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.CustomerOrder;
import com.stock.control.model.CustomerOrder_;
import com.stock.control.model.Customer_;

public class CustomerOrderSpecifications {

	private static Logger logger = LoggerFactory.getLogger(CustomerOrderSpecifications.class);

	public static Specification<CustomerOrder> customer(final String pattern) {
		logger.debug("CustomerOrder: customer");
		Specification<CustomerOrder> spec = new Specification<CustomerOrder>() {
			@Override
			public Predicate toPredicate(Root<CustomerOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(root.get(CustomerOrder_.customer).get(Customer_.descr), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<CustomerOrder> dateOrder(final Date pattern) {
		logger.debug("CustomerOrder: dateOrder");
		Specification<CustomerOrder> spec = new Specification<CustomerOrder>() {
			@Override
			public Predicate toPredicate(Root<CustomerOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get(CustomerOrder_.dateOrder), pattern);
			}
		};
		return spec;
	}

}