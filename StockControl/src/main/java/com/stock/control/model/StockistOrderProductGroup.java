package com.stock.control.model;

import java.io.Serializable;

public class StockistOrderProductGroup implements Serializable {

	private static final long serialVersionUID = 7024922232472343750L;

	private int amount;

	private StockistOrderProduct stockistOrderProduct;

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public StockistOrderProduct getStockistOrderProduct() {
		return stockistOrderProduct;
	}

	public void setStockistOrderProduct(StockistOrderProduct stockistOrderProduct) {
		this.stockistOrderProduct = stockistOrderProduct;
	}

}
