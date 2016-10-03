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

import com.stock.control.dao.dynamic.filter.CustomerOrderSearchFilter;
import com.stock.control.model.CustomerOrder;
import com.stock.control.service.CustomerOrderService;
import com.stock.control.utils.CommonUtils;

public class CustomerOrderLazyListDataModel extends LazyDataModel<CustomerOrder> {

	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(CustomerOrderLazyListDataModel.class);

	private CustomerOrderService customerOrderService;
	private List<CustomerOrder> dataModel;

	public CustomerOrderLazyListDataModel(CustomerOrderService customerOrderService) {
		this.customerOrderService = customerOrderService;
	}

	@Override
	public List<CustomerOrder> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		log.debug("call CustomerOrderLazyListDataModel load");
		CustomerOrderSearchFilter customerOrderFilter = new CustomerOrderSearchFilter();
		customerOrderFilter = this.createDataFilter(filters, customerOrderFilter);
		customerOrderFilter = this.setSortOrderToDataFilter(customerOrderFilter, sortField, sortOrder);
		Page<CustomerOrder> page = customerOrderService.findCustomerOrderFilter(customerOrderFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	private CustomerOrderSearchFilter setSortOrderToDataFilter(CustomerOrderSearchFilter dataFilter, String sortField, SortOrder sortOrder) {
		dataFilter.initDefaultFilter();
		return dataFilter;
	}

	private CustomerOrderSearchFilter createDataFilter(Map<String, Object> filters, CustomerOrderSearchFilter filter) {

		if (filters != null) {
			for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
				String filterProperty = it.next();
				Object filterValue = filters.get(filterProperty);
				if ("customer.descr".equals(filterProperty)) {
					filter.setCustomer((String) filterValue);
				} else if ("dateOrder".equals(filterProperty)) {
					filter.setDateOrder((Date) filterValue);
				}
			}
		}

		return filter;
	}

	@Override
	public CustomerOrder getRowData(String rowKey) {
		for (CustomerOrder s : dataModel) {
			if (s.getIdCustomerOrder() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(CustomerOrder object) {
		return object.getIdCustomerOrder();
	}

}
