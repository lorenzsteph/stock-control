package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link_category_product")
public class LinkCategoryProduct implements Serializable {

	private static final long serialVersionUID = 3881807203256643373L;

	@Id
	@Column(name = "id_category")
	private Long idCategory;

	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public Long getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

}
