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
@Table(name = "link_product_range")
public class LinkProductRange implements Serializable {

	private static final long serialVersionUID = 4890735186298115601L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_link_product_range")
	private Long idLinkProductRange;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_range")
	private Range range;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_product")
	private Product product;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdLinkProductRange() {
		return idLinkProductRange;
	}

	public void setIdLinkProductRange(Long idLinkProductRange) {
		this.idLinkProductRange = idLinkProductRange;
	}

	public Range getRange() {
		return range;
	}

	public void setRange(Range range) {
		this.range = range;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

}
