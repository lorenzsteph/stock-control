package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.StorehouseSearchFilter;
import com.stock.control.dao.dynamic.query.StorehouseSpecifications;
import com.stock.control.model.Storehouse;

public class StorehouseQueryCommand implements QueryCommand<StorehouseSearchFilter, Storehouse> {

	@Override
	public Specifications<Storehouse> where(StorehouseSearchFilter filter) {
		Specifications<Storehouse> spec = null;

		if (filter != null) {
			if (filter.getCategory() != null) {
				spec = Specifications.where(StorehouseSpecifications.descr(filter.getCategory().toLowerCase()));
			}
		}

		return spec;
	}

}