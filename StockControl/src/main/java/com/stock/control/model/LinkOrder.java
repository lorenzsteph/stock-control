package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "link_order")
public class LinkOrder implements Serializable {

	private static final long serialVersionUID = 1666813845981148173L;

	@Id
	@Column(name = "id_link_order")
	private Long idLinkOrder;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_customer_order")
	private CustomerOrder customerOrder;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stockist_order_product")
	private StockistOrderProduct stockistOrderProduct;

	@Column(name = "real_selling_price")
	private BigDecimal realSellingPrice;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdLinkOrder() {
		return idLinkOrder;
	}

	public void setIdLinkOrder(Long idLinkOrder) {
		this.idLinkOrder = idLinkOrder;
	}

	public BigDecimal getRealSellingPrice() {
		return realSellingPrice;
	}

	public void setRealSellingPrice(BigDecimal realSellingPrice) {
		this.realSellingPrice = realSellingPrice;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public CustomerOrder getCustomerOrder() {
		return customerOrder;
	}

	public void setCustomerOrder(CustomerOrder customerOrder) {
		this.customerOrder = customerOrder;
	}

	public StockistOrderProduct getStockistOrderProduct() {
		return stockistOrderProduct;
	}

	public void setStockistOrderProduct(StockistOrderProduct stockistOrderProduct) {
		this.stockistOrderProduct = stockistOrderProduct;
	}

}
