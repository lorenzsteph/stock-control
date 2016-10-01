package com.stock.control.model;

import java.io.Serializable;
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
@Table(name = "link_brand_category")
public class LinkBrandCategory implements Serializable {

	private static final long serialVersionUID = -3009138290777498058L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_link_brand_category")
	private Long idLinkBrandCategory;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_brand")
	private Brand brand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category")
	private Category category;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Long getIdLinkBrandCategory() {
		return idLinkBrandCategory;
	}

	public void setIdLinkBrandCategory(Long idLinkBrandCategory) {
		this.idLinkBrandCategory = idLinkBrandCategory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
