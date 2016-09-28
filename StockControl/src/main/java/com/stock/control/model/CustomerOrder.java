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
@Table(name = "customer_order")
public class CustomerOrder implements Serializable {

	private static final long serialVersionUID = -914216728709192126L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_customer_order")
	private Long idCustomerOrder;

	@Column(name = "id_customer")
	private Long idCustomer;

	@Column(name = "date_order")
	private Date dateOrder;

	@Column(name = "price_order")
	private BigDecimal priceOrder;

	@Column(name = "real_order_price")
	private BigDecimal realOrderPrice;

	@Column(name = "discount_percentage")
	private BigDecimal discountPercentage;

	@Column(name = "note")
	private String note;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdCustomerOrder() {
		return idCustomerOrder;
	}

	public void setIdCustomerOrder(Long idCustomerOrder) {
		this.idCustomerOrder = idCustomerOrder;
	}

	public Long getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(Long idCustomer) {
		this.idCustomer = idCustomer;
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

	public BigDecimal getRealOrderPrice() {
		return realOrderPrice;
	}

	public void setRealOrderPrice(BigDecimal realOrderPrice) {
		this.realOrderPrice = realOrderPrice;
	}

	public BigDecimal getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(BigDecimal discountPercentage) {
		this.discountPercentage = discountPercentage;
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

}
