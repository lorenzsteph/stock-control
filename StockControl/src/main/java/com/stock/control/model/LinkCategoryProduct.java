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
@Table(name = "link_category_product")
public class LinkCategoryProduct implements Serializable {

	private static final long serialVersionUID = 3881807203256643373L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_link_category_product")
	private Long idLinkCategoryProduct;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_category")
	private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Long getIdLinkCategoryProduct() {
		return idLinkCategoryProduct;
	}

	public void setIdLinkCategoryProduct(Long idLinkCategoryProduct) {
		this.idLinkCategoryProduct = idLinkCategoryProduct;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
