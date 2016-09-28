package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link_brand_category")
public class LinkBrandCategory implements Serializable {

	private static final long serialVersionUID = -3009138290777498058L;

	@Id
	@Column(name = "id_brand")
	private Long idBrand;

	@Column(name = "id_category")
	private Long idCategory;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

}
