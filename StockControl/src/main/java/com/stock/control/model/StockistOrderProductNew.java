package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class StockistOrderProductNew implements Serializable {

	private static final long serialVersionUID = 7024922232472343750L;

	private BigDecimal price;
	private BigDecimal amount;
	private BigDecimal priceIva;
	private BigDecimal priceForAmount;
	private BigDecimal priceForAmountIva;

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPriceIva() {
		return priceIva;
	}

	public void setPriceIva(BigDecimal priceIva) {
		this.priceIva = priceIva;
	}

	public BigDecimal getPriceForAmount() {
		return priceForAmount;
	}

	public void setPriceForAmount(BigDecimal priceForAmount) {
		this.priceForAmount = priceForAmount;
	}

	public BigDecimal getPriceForAmountIva() {
		return priceForAmountIva;
	}

	public void setPriceForAmountIva(BigDecimal priceForAmountIva) {
		this.priceForAmountIva = priceForAmountIva;
	}

}
