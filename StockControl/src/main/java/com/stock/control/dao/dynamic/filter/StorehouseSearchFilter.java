package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.stock.control.model.Storehouse_;

public class StorehouseSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private Map<String, Object> filters;

	private List<Order> order;

	public StorehouseSearchFilter() {

		initDefaultFilter();
	}

	public void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.ASC, Storehouse_.idProductForOrder.getName());
		order.add(orderName);

		setOrder(order);

	}

	public void addOrder(String sortField, String sortOrder) {
		order = new ArrayList<Order>();
	
		Order orderName = new Order(sortOrder.equals("ASCENDING") ? Direction.ASC : Direction.DESC, sortField);
		order.add(orderName);
		
		orderName = new Order(Direction.ASC, Storehouse_.idProductForOrder.getName());
		order.add(orderName);
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Map<String, Object> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, Object> filters) {
		this.filters = filters;
	}

	public String getCategory() {
		return (String) filters.get("category");
	}

	public String getStockist() {
		return (String) filters.get("stockist");
	}

	public String getBrand() {
		return (String) filters.get("brand");
	}

	public String getCodProduct() {
		return (String) filters.get("codProduct");
	}

	public String getProduct() {
		return (String) filters.get("product");
	}

	public String getRange() {
		return (String) filters.get("range");
	}

}
