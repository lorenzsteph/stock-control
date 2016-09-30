package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.stock.control.dao.dynamic.filter.CustomerSearchFilter;
import com.stock.control.model.Customer;
import com.stock.control.service.CustomerService;
import com.stock.control.utils.CommonUtils;

public class CustomerLazyListDataModel extends LazyDataModel<Customer> {

	private static final long serialVersionUID = 1L;

	private CustomerService customerService;
	private List<Customer> dataModel;

	public CustomerLazyListDataModel(CustomerService customerService) {
		this.customerService = customerService;

	}

	@Override
	public List<Customer> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		CustomerSearchFilter customerFilter = new CustomerSearchFilter();

		Page<Customer> page = customerService.findCustomerFilter(customerFilter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	@Override
	public Customer getRowData(String rowKey) {
		for (Customer c : dataModel) {
			if (c.getIdCustomer() == Long.parseLong(rowKey)) {
				return c;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Customer object) {
		return object.getIdCustomer();
	}

}
