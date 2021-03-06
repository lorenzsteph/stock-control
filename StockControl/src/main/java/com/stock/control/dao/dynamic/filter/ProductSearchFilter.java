package com.stock.control.dao.dynamic.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.stock.control.model.Product_;

public class ProductSearchFilter implements Serializable {

	private static final long serialVersionUID = -1585612142057165754L;

	private long idCategory;
	private long idBrand;

	private List<Order> order;

	public ProductSearchFilter() {

		initDefaultFilter();
	}

	public void initDefaultFilter() {
		List<Order> order = new ArrayList<Order>();

		Order orderName = new Order(Direction.ASC, Product_.codProduct.getName());
		order.add(orderName);

		setOrder(order);

	}

	public void addOrder(String sortField, String sortOrder) {
		order = new ArrayList<Order>();
		Order orderName = new Order(sortOrder.equals("ASCENDING") ? Direction.ASC : Direction.DESC, sortField);
		order.add(orderName);

		orderName = new Order(Direction.ASC, Product_.idProduct.getName());
		order.add(orderName);
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

	public long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(long idBrand) {
		this.idBrand = idBrand;
	}

}
