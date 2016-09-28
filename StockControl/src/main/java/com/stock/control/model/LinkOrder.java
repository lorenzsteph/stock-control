package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link_order")
public class LinkOrder implements Serializable {

	private static final long serialVersionUID = 1666813845981148173L;

	@Id
	@Column(name = "id_link_order")
	private Long idLinkOrder;

	@Column(name = "id_customer_order")
	private Long idCustomerOrder;

	@Column(name = "id_stockist_order_product")
	private Long idStockistOrderProduct;

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

	public Long getIdCustomerOrder() {
		return idCustomerOrder;
	}

	public void setIdCustomerOrder(Long idCustomerOrder) {
		this.idCustomerOrder = idCustomerOrder;
	}

	public Long getIdStockistOrderProduct() {
		return idStockistOrderProduct;
	}

	public void setIdStockistOrderProduct(Long idStockistOrderProduct) {
		this.idStockistOrderProduct = idStockistOrderProduct;
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

}
