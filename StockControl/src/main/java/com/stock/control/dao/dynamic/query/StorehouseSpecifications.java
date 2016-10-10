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

	public static Specification<Storehouse> category(final String pattern) {
		logger.debug("Storehouse: category");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.category)), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<Storehouse> stockist(final String pattern) {
		logger.debug("Storehouse: stockist");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.stockist)), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<Storehouse> brand(final String pattern) {
		logger.debug("Storehouse: brand");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.brand)), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<Storehouse> codProduct(final String pattern) {
		logger.debug("Storehouse: codProduct");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.codProduct)), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<Storehouse> product(final String pattern) {
		logger.debug("Storehouse: product");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.product)), pattern + "%");
			}
		};
		return spec;
	}

	public static Specification<Storehouse> range(final String pattern) {
		logger.debug("Storehouse: range");
		Specification<Storehouse> spec = new Specification<Storehouse>() {
			@Override
			public Predicate toPredicate(Root<Storehouse> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.like(cb.lower(root.get(Storehouse_.range)), pattern + "%");
			}
		};
		return spec;
	}

}