package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stockist_order_product")
public class StockistOrderProduct implements Serializable {

	private static final long serialVersionUID = 7024922232472343750L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_stockist_order_product")
	private Long idStockistOrderProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stockist_order")
	private StockistOrder stockistOrder;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "descr")
	private String descr;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "stockistOrderProduct")
	private LinkOrder linkOrder;

	public Long getIdStockistOrderProduct() {
		return idStockistOrderProduct;
	}

	public void setIdStockistOrderProduct(Long idStockistOrderProduct) {
		this.idStockistOrderProduct = idStockistOrderProduct;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public StockistOrder getStockistOrder() {
		return stockistOrder;
	}

	public void setStockistOrder(StockistOrder stockistOrder) {
		this.stockistOrder = stockistOrder;
	}

	public LinkOrder getLinkOrder() {
		return linkOrder;
	}

	public void setLinkOrder(LinkOrder linkOrder) {
		this.linkOrder = linkOrder;
	}

}
