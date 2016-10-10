package com.stock.control.dao.dynamic.command;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import com.stock.control.dao.dynamic.filter.StockistOrderSearchFilter;
import com.stock.control.dao.dynamic.query.StockistOrderSpecifications;
import com.stock.control.model.StockistOrder;

public class StockistOrderQueryCommand implements QueryCommand<StockistOrderSearchFilter, StockistOrder> {

	@Override
	public Specifications<StockistOrder> where(StockistOrderSearchFilter filter) {
		Specifications<StockistOrder> spec = null;

		if (filter != null) {
			if (filter.getStockist() != null) {
				spec = setSpecification(spec, StockistOrderSpecifications.stockist(filter.getStockist()));
			}

			if (filter.getDateOrder() != null) {
				spec = setSpecification(spec, StockistOrderSpecifications.dateOrder(filter.getDateOrder()));
			}
		}

		return spec;
	}

	private Specifications<StockistOrder> setSpecification(Specifications<StockistOrder> spec, Specification<StockistOrder> specification) {
		if (spec == null) {
			spec = Specifications.where(specification);
		} else {
			spec = spec.and(specification);
		}
		return spec;
	}
}