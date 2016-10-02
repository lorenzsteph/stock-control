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
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product implements Serializable {

	private static final long serialVersionUID = -4652951600994096526L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "cod_product")
	private String codProduct;

	@Column(name = "descr")
	private String descr;

	@Column(name = "range")
	private String range;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_category")
	private Category category;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public String getCodProduct() {
		return codProduct;
	}

	public void setCodProduct(String codProduct) {
		this.codProduct = codProduct;
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

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
