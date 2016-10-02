package com.stock.control.dao.dynamic.command;

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
				spec = Specifications.where(ProductSpecifications.idCategory(filter.getIdCategory()));
			}
		}

		return spec;
	}

}