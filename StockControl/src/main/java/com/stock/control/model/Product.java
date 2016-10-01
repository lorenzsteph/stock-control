package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkProductRange> linkProductRange;

	@OneToMany(mappedBy = "product", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkCategoryProduct> linkCategoryProduct;

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

	public Set<LinkCategoryProduct> getLinkCategoryProduct() {
		return linkCategoryProduct;
	}

	public void setLinkCategoryProduct(Set<LinkCategoryProduct> linkCategoryProduct) {
		this.linkCategoryProduct = linkCategoryProduct;
	}

	public Set<LinkProductRange> getLinkProductRange() {
		return linkProductRange;
	}

	public void setLinkProductRange(Set<LinkProductRange> linkProductRange) {
		this.linkProductRange = linkProductRange;
	}

}
