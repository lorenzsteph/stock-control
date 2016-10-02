package com.stock.control.dao.dynamic.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.Storehouse;
import com.stock.control.model.Storehouse_;

public class StorehouseSpecifications {

	private static Logger logger = LoggerFactory.getLogger(StorehouseSpecifications.class);

	public static Specification<Storehouse> descr(final String pattern) {
		logger.debug("Storehouse: category");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(cb.lower(root.get(Storehouse_.category)), pattern);
			}
		};
		return spec;
	}

}