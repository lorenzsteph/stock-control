package com.stock.control.controller.pf.datamodel;

import java.util.Iterator;
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

		StockistSearchFilter stockistFilter = new StockistSearchFilter();
		stockistFilter = this.createDataFilter(filters, stockistFilter);
		stockistFilter = this.setSortOrderToDataFilter(stockistFilter, sortField, sortOrder);

		Page<Stockist> page = stockistService.findStockistFilter(stockistFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	private StockistSearchFilter setSortOrderToDataFilter(StockistSearchFilter dataFilter, String sortField, SortOrder sortOrder) {
		dataFilter.initDefaultFilter();
		if(sortField!=null){
			dataFilter.addOrder(sortField, sortOrder.toString());
		}
		return dataFilter;
	}
	
	private StockistSearchFilter createDataFilter(Map<String, Object> filters, StockistSearchFilter filter) {

		if (filters != null) {
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				String filterProperty = it.next();
				Object filterValue = filters.get(filterProperty);
				if ("stockist.descr".equals(filterProperty)) {
					filter.setDescr((String) filterValue);
				}
			}
		}

		return filter;
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
