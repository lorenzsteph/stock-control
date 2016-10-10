package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.stock.control.model.CustomerOrder_;

public class StockistOrderSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private Date dateOrder;
	private String stockist;

	private List<Order> order;

	public StockistOrderSearchFilter() {

		initDefaultFilter();
	}

	public void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.DESC, CustomerOrder_.dateOrder.getName());
		order.add(orderName);

		setOrder(order);

	}

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public String getStockist() {
		return stockist;
	}

	public void setStockist(String stockist) {
		this.stockist = stockist;
	}

}
