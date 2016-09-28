package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link_product_range")
public class LinkProductRange implements Serializable {

	private static final long serialVersionUID = 4890735186298115601L;

	@Id
	@Column(name = "id_rang")
	private Long idRang;

	@Column(name = "id_product")
	private Long idProduct;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdRang() {
		return idRang;
	}

	public void setIdRang(Long idRang) {
		this.idRang = idRang;
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
