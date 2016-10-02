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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 5483575320570035939L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_category")
	private Long idCategory;

	@Column(name = "descr")
	private String descr;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_brand")
	private Brand brand;

	@OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<Product> product;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Set<Product> getProduct() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}

}
