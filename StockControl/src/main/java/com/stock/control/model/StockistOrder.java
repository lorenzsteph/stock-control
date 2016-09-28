package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stockist_order")
public class StockistOrder implements Serializable {

	private static final long serialVersionUID = 5934958539947557350L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_stockist_order")
	private Long idStockistOrder;

	@Column(name = "note")
	private String note;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	@Column(name = "date_order")
	private Date dateOrder;

	@Column(name = "price_order")
	private BigDecimal priceOrder;

	@Column(name = "shipping_charges")
	private BigDecimal shippingCharges;

	@Column(name = "other_shopping")
	private BigDecimal otherShopping;

	public Long getIdStockistOrder() {
		return idStockistOrder;
	}

	public void setIdStockistOrder(Long idStockistOrder) {
		this.idStockistOrder = idStockistOrder;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public BigDecimal getPriceOrder() {
		return priceOrder;
	}

	public void setPriceOrder(BigDecimal priceOrder) {
		this.priceOrder = priceOrder;
	}

	public BigDecimal getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(BigDecimal shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public BigDecimal getOtherShopping() {
		return otherShopping;
	}

	public void setOtherShopping(BigDecimal otherShopping) {
		this.otherShopping = otherShopping;
	}

}
