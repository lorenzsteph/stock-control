package com.stock.control.dao.dynamic.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.Stockist;
import com.stock.control.model.Stockist_;

public class StockistSpecifications {

	private static Logger logger = LoggerFactory.getLogger(StockistSpecifications.class);

	public static Specification<Stockist> descr(final String pattern) {
		logger.debug("Stockist: description");
		Specification<Stockist> spec = new Specification<Stockist>() {
			@Override
			public Predicate toPredicate(Root<Stockist> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(cb.lower(root.get(Stockist_.descr)), pattern);
			}
		};
		return spec;
	}

}