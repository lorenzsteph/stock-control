package com.stock.control.controller.pf;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.model.Brand;
import com.stock.control.model.Category;
import com.stock.control.model.CustomerOrder;
import com.stock.control.model.Product;
import com.stock.control.model.Stockist;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SelectedRecordBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private Stockist stockist;

	private Brand brand;

	private Category category;

	private Product product;

	private CustomerOrder customerOrder;

	public Stockist getStockist() {
		return stockist;
	}

	public void setStockist(Stockist stockist) {
		this.stockist = stockist;
		this.brand = null;
		this.category = null;
		this.product = null;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
		this.category = null;
		this.product = null;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
		this.product = null;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

}
