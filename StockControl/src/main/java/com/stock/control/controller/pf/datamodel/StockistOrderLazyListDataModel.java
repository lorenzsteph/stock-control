package com.stock.control.controller.pf.datamodel;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StockistOrderSearchFilter;
import com.stock.control.model.StockistOrder;
import com.stock.control.service.StockistOrderService;
import com.stock.control.utils.CommonUtils;

public class StockistOrderLazyListDataModel extends LazyDataModel<StockistOrder> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(StockistOrderLazyListDataModel.class);

	private StockistOrderService stockistOrderService;
	private List<StockistOrder> dataModel;

	public StockistOrderLazyListDataModel(StockistOrderService stockistOrderService) {
		this.stockistOrderService = stockistOrderService;
	}

	@Override
	public List<StockistOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		log.debug("call StockistOrderLazyListDataModel load");
		StockistOrderSearchFilter stockistOrderFilter = new StockistOrderSearchFilter();
		stockistOrderFilter = this.createDataFilter(filters, stockistOrderFilter);
		Page<StockistOrder> page = stockistOrderService.findStockistOrderFilter(stockistOrderFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		stockistOrderFilter = this.setSortOrderToDataFilter(stockistOrderFilter, sortField, sortOrder);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	private StockistOrderSearchFilter setSortOrderToDataFilter(StockistOrderSearchFilter dataFilter, String sortField, SortOrder sortOrder) {
		dataFilter.initDefaultFilter();
		return dataFilter;
	}

	private StockistOrderSearchFilter createDataFilter(Map<String, Object> filters, StockistOrderSearchFilter filter) {

		if (filters != null) {
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				String filterProperty = it.next();
				Object filterValue = filters.get(filterProperty);
				if ("stockist.descr".equals(filterProperty)) {
					filter.setStockist((String) filterValue);
				} else if ("dateOrder".equals(filterProperty)) {
					filter.setDateOrder((Date) filterValue);
				}
			}
		}

		return filter;
	}

	@Override
	public StockistOrder getRowData(String rowKey) {
		for (StockistOrder s : dataModel) {
			if (s.getIdStockistOrder() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(StockistOrder object) {
		return object.getIdStockistOrder();
	}

}
