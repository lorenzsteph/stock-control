package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class StorehouseSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private String category;

	private List<Order> order;

	public StorehouseSearchFilter() {

		initDefaultFilter();
	}

	public void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.ASC, "id");
		order.add(orderName);

		setOrder(order);

	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
