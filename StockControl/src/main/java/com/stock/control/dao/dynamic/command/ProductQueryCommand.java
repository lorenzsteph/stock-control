package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.ProductSearchFilter;
import com.stock.control.dao.dynamic.query.ProductSpecifications;
import com.stock.control.model.Product;

public class ProductQueryCommand implements QueryCommand<ProductSearchFilter, Product> {

	@Override
	public Specifications<Product> where(ProductSearchFilter filter) {
		Specifications<Product> spec = null;

		if (filter != null) {
			if (filter.getIdCategory() != 0L) {
				spec = setSpecification(spec, ProductSpecifications.idCategory(filter.getIdCategory()));
			}

			if (filter.getIdBrand() != 0L) {
				spec = setSpecification(spec, ProductSpecifications.idBrand(filter.getIdBrand()));
			}
		}

		return spec;
	}

	private Specifications<Product> setSpecification(Specifications<Product> spec, Specification<Product> specification) {
		if (spec == null) {
			spec = Specifications.where(specification);
		} else {
			spec = spec.and(specification);
		}
		return spec;
	}

}