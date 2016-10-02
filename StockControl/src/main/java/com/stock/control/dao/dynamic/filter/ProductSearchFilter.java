package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class ProductSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private long idCategory;

	private List<Order> order;

	public ProductSearchFilter() {

		initDefaultFilter();
	}

	private void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.ASC, "idProduct");
		order.add(orderName);

		setOrder(order);

	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(long idCategory) {
		this.idCategory = idCategory;
	}

}
