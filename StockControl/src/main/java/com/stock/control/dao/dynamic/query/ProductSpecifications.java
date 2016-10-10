package com.stock.control.dao.dynamic.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.Brand_;
import com.stock.control.model.Category_;
import com.stock.control.model.Product;
import com.stock.control.model.Product_;

public class ProductSpecifications {

	private static Logger logger = LoggerFactory.getLogger(ProductSpecifications.class);

	public static Specification<Product> idCategory(final long idCategory) {
		logger.debug("Product: idCategory");
		Specification<Product> spec = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get(Product_.category).get(Category_.idCategory), idCategory);
			}
		};
		return spec;
	}

	public static Specification<Product> idBrand(final long idBrand) {
		logger.debug("Product: idBrand");
		Specification<Product> spec = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get(Product_.brand).get(Brand_.idBrand), idBrand);
			}
		};
		return spec;
	}

}