package com.stock.control.controller.pf.datamodel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.StorehouseSearchFilter;
import com.stock.control.model.Stockist;
import com.stock.control.model.Storehouse;
import com.stock.control.service.StorehouseService;
import com.stock.control.utils.CommonUtils;

public class StorehouseLazyListDataModel extends LazyDataModel<Storehouse> {

	private static final long serialVersionUID = 1L;

	private StorehouseService storehouseService;
	private List<Storehouse> dataModel;
	private StorehouseSearchFilter storehouseFilter;
	private String stockistFilter;

	public StorehouseLazyListDataModel(StorehouseService storehouseService) {
		this.storehouseService = storehouseService;
		storehouseFilter = new StorehouseSearchFilter();
	}

	public StorehouseLazyListDataModel(StorehouseService storehouseService, Stockist stockist) {
		this.storehouseService = storehouseService;
		storehouseFilter = new StorehouseSearchFilter();

		stockistFilter = stockist.getDescr();
	}

	@Override
	public List<Storehouse> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		storehouseFilter = this.createDataFilter(filters, storehouseFilter);
		storehouseFilter = this.setSortOrderToDataFilter(storehouseFilter, sortField, sortOrder);
		Page<Storehouse> page = storehouseService.findStorehouseFilter(storehouseFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	private StorehouseSearchFilter setSortOrderToDataFilter(StorehouseSearchFilter dataFilter, String sortField, SortOrder sortOrder) {
		dataFilter.initDefaultFilter();
		if (sortField != null) {
			dataFilter.addOrder(sortField, sortOrder.toString());
		}
		return dataFilter;
	}

	private StorehouseSearchFilter createDataFilter(Map<String, Object> filters, StorehouseSearchFilter storehouseFilter) {
		storehouseFilter = new StorehouseSearchFilter();
		if (filters == null) {
			filters = new HashMap<String, Object>();
		}
		if (stockistFilter != null) {
			filters.put("stockist", stockistFilter);
		}
		storehouseFilter.setFilters(filters);
		return storehouseFilter;
	}

	@Override
	public Storehouse getRowData(String rowKey) {
		for (Storehouse s : dataModel) {
			if (s.getId() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Storehouse object) {
		return object.getId();
	}

}
