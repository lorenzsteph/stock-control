package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.StorehouseSearchFilter;
import com.stock.control.dao.dynamic.query.StorehouseSpecifications;
import com.stock.control.model.Storehouse;

public class StorehouseQueryCommand implements QueryCommand<StorehouseSearchFilter, Storehouse> {

	@Override
	public Specifications<Storehouse> where(StorehouseSearchFilter filter) {
		Specifications<Storehouse> spec = null;

		if (filter != null) {
			if (filter.getStockist() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.stockist(filter.getStockist().toLowerCase()));
			}
			if (filter.getBrand() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.brand(filter.getBrand().toLowerCase()));
			}
			if (filter.getCategory() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.category(filter.getCategory().toLowerCase()));
			}
			if (filter.getCodProduct() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.codProduct(filter.getCodProduct().toLowerCase()));
			}
			if (filter.getProduct() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.product(filter.getProduct().toLowerCase()));
			}
			if (filter.getRange() != null) {
				spec = setSpecification(spec, StorehouseSpecifications.range(filter.getRange().toLowerCase()));
			}
		}

		return spec;
	}

	private Specifications<Storehouse> setSpecification(Specifications<Storehouse> spec, Specification<Storehouse> specification) {
		if (spec == null) {
			spec = Specifications.where(specification);
		} else {
			spec = spec.and(specification);
		}
		return spec;
	}

}