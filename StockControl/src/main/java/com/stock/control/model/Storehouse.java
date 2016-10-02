package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "storehouse")
public class Storehouse implements Serializable {

	private static final long serialVersionUID = 3733215513761478725L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id")
	private long id;

	@Column(name = "stockist")
	private String stockist;

	@Column(name = "brand")
	private String brand;

	@Column(name = "category")
	private String category;

	@Column(name = "cod_product")
	private String codProduct;

	@Column(name = "product")
	private String product;

	@Column(name = "range")
	private String range;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@Column(name = "price_order")
	private BigDecimal priceOrder;

	@Column(name = "store_total")
	private BigDecimal storeTotal;

	@Column(name = "store_price")
	private BigDecimal storePrice;

	public String getStockist() {
		return stockist;
	}

	public void setStockist(String stockist) {
		this.stockist = stockist;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getCodProduct() {
		return codProduct;
	}

	public void setCodProduct(String codProduct) {
		this.codProduct = codProduct;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public BigDecimal getPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(BigDecimal priceOrder) {
		this.priceOrder = priceOrder;
	}

	public BigDecimal getStoreTotal() {
		return storeTotal;
	}

	public void setStoreTotal(BigDecimal storeTotal) {
		this.storeTotal = storeTotal;
	}

	public BigDecimal getStorePrice() {
		return storePrice;
	}

	public void setStorePrice(BigDecimal storePrice) {
		this.storePrice = storePrice;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
