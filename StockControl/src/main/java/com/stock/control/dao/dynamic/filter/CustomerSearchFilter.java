package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class CustomerSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private String descr;

	private List<Order> order;

	public CustomerSearchFilter() {

		initDefaultFilter();
	}

	private void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.ASC, "idCustomer");
		order.add(orderName);

		setOrder(order);

	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

}
