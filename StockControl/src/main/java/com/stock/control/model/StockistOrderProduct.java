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
@Table(name = "stockist_order_product")
public class StockistOrderProduct implements Serializable {

	private static final long serialVersionUID = 7024922232472343750L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_stockist_order_product")
	private Long idStockistOrderProduct;

	@Column(name = "id_stockist_order")
	private BigDecimal idStockistOrder;

	@Column(name = "id_stockist")
	private BigDecimal idStockist;

	@Column(name = "id_brand")
	private BigDecimal idBrand;

	@Column(name = "id_category")
	private BigDecimal idCategory;

	@Column(name = "id_product")
	private BigDecimal idProduct;

	@Column(name = "id_range")
	private BigDecimal idRange;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "descr")
	private String descr;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdStockistOrderProduct() {
		return idStockistOrderProduct;
	}

	public void setIdStockistOrderProduct(Long idStockistOrderProduct) {
		this.idStockistOrderProduct = idStockistOrderProduct;
	}

	public BigDecimal getIdStockistOrder() {
		return idStockistOrder;
	}

	public void setIdStockistOrder(BigDecimal idStockistOrder) {
		this.idStockistOrder = idStockistOrder;
	}

	public BigDecimal getIdStockist() {
		return idStockist;
	}

	public void setIdStockist(BigDecimal idStockist) {
		this.idStockist = idStockist;
	}

	public BigDecimal getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(BigDecimal idBrand) {
		this.idBrand = idBrand;
	}

	public BigDecimal getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(BigDecimal idCategory) {
		this.idCategory = idCategory;
	}

	public BigDecimal getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(BigDecimal idProduct) {
		this.idProduct = idProduct;
	}

	public BigDecimal getIdRange() {
		return idRange;
	}

	public void setIdRange(BigDecimal idRange) {
		this.idRange = idRange;
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

}
