package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.LinkCategoryProductSearchFilter;
import com.stock.control.dao.dynamic.query.LinkCategoryProductSpecifications;
import com.stock.control.model.LinkCategoryProduct;

public class LinkCategoryProductQueryCommand implements QueryCommand<LinkCategoryProductSearchFilter, LinkCategoryProduct> {

	@Override
	public Specifications<LinkCategoryProduct> where(LinkCategoryProductSearchFilter filter) {
		Specifications<LinkCategoryProduct> spec = null;

		if (filter != null) {
			if (filter.getIdCategory() != 0L) {
				spec = Specifications.where(LinkCategoryProductSpecifications.idCategory(filter.getIdCategory()));
			}
		}

		return spec;
	}

}