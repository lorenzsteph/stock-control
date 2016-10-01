package com.stock.control.dao.dynamic.query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import com.stock.control.model.Category_;
import com.stock.control.model.LinkCategoryProduct;
import com.stock.control.model.LinkCategoryProduct_;

public class LinkCategoryProductSpecifications {

	private static Logger logger = LoggerFactory.getLogger(LinkCategoryProductSpecifications.class);

	public static Specification<LinkCategoryProduct> idCategory(final long idCategory) {
		logger.debug("LinkCategoryProduct: idCategory");
		Specification<LinkCategoryProduct> spec = new Specification<LinkCategoryProduct>() {
			@Override
			public Predicate toPredicate(Root<LinkCategoryProduct> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
				return cb.equal(root.get(LinkCategoryProduct_.category).get(Category_.idCategory), idCategory);
			}
		};
		return spec;
	}

}