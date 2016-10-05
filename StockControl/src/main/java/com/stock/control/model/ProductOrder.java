package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductOrder implements Serializable {

	private static final long serialVersionUID = -4652951600994096526L;

	private int amount;

	private Product product;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
