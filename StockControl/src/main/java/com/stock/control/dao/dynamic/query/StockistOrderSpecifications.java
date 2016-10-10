package com.stock.control.dao.dynamic.query;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.StockistOrder;
import com.stock.control.model.StockistOrder_;
import com.stock.control.model.Stockist_;

public class StockistOrderSpecifications {

	private static Logger logger = LoggerFactory.getLogger(StockistOrderSpecifications.class);

	public static Specification<StockistOrder> stockist(final String pattern) {
		logger.debug("StockistOrder: stockist");
		Specification<StockistOrder> spec = new Specification<StockistOrder>() {
			@Override
			public Predicate toPredicate(Root<StockistOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(root.get(StockistOrder_.stockist).get(Stockist_.descr), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<StockistOrder> dateOrder(final Date pattern) {
		logger.debug("StockistOrder: dateOrder");
		Specification<StockistOrder> spec = new Specification<StockistOrder>() {
			@Override
			public Predicate toPredicate(Root<StockistOrder> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get(StockistOrder_.dateOrder), pattern);
			}
		};
		return spec;
	}

}