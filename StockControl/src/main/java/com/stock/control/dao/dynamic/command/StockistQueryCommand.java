package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.StockistSearchFilter;
import com.stock.control.dao.dynamic.query.StockistSpecifications;
import com.stock.control.model.Stockist;

public class StockistQueryCommand implements QueryCommand<StockistSearchFilter, Stockist> {

	@Override
	public Specifications<Stockist> where(StockistSearchFilter filter) {
		Specifications<Stockist> spec = null;

		if (filter != null) {
			if (filter.getDescr() != null) {
				spec = Specifications.where(StockistSpecifications.descr(filter.getDescr().toLowerCase()));
			}
		}

		return spec;
	}

}