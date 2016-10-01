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
@Table(name = "category")
public class Category implements Serializable {

	private static final long serialVersionUID = 5483575320570035939L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_category")
	private Long idCategory;

	@Column(name = "descr")
	private String descr;

	@OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkBrandCategory> linkBrandCategory;

	@OneToMany(mappedBy = "category", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkCategoryProduct> linkCategoryProduct;

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

	public Set<LinkBrandCategory> getLinkBrandCategory() {
		return linkBrandCategory;
	}

	public void setLinkBrandCategory(Set<LinkBrandCategory> linkBrandCategory) {
		this.linkBrandCategory = linkBrandCategory;
	}

	public Set<LinkCategoryProduct> getLinkCategoryProduct() {
		return linkCategoryProduct;
	}

	public void setLinkCategoryProduct(Set<LinkCategoryProduct> linkCategoryProduct) {
		this.linkCategoryProduct = linkCategoryProduct;
	}

}
