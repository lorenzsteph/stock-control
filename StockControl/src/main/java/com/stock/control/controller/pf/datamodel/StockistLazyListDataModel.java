package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StockistSearchFilter;
import com.stock.control.model.Stockist;
import com.stock.control.service.StockistService;
import com.stock.control.utils.CommonUtils;

public class StockistLazyListDataModel extends LazyDataModel<Stockist> {

	private static final long serialVersionUID = 1L;

	private StockistService stockistService;
	private List<Stockist> dataModel;

	public StockistLazyListDataModel(StockistService stockistService) {
		this.stockistService = stockistService;

	}

	@Override
	public List<Stockist> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		StockistSearchFilter StockistFilter = new StockistSearchFilter();

		Page<Stockist> page = stockistService.findStockistFilter(StockistFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	@Override
	public Stockist getRowData(String rowKey) {
		for (Stockist s : dataModel) {
			if (s.getIdStockist() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Stockist object) {
		return object.getIdStockist();
	}

}
