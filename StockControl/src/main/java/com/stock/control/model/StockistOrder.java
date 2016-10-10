package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stockist")
	private Stockist stockist;

	@OneToMany(mappedBy = "stockistOrder", fetch = FetchType.EAGER)
	private Set<StockistOrderProduct> stockistOrderProduct;

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

	public Stockist getStockist() {
		return stockist;
	}

	public void setStockist(Stockist stockist) {
		this.stockist = stockist;
	}

	public Set<StockistOrderProduct> getStockistOrderProduct() {
		return stockistOrderProduct;
	}

	public void setStockistOrderProduct(Set<StockistOrderProduct> stockistOrderProduct) {
		this.stockistOrderProduct = stockistOrderProduct;
	}

}
