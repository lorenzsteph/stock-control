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
@Table(name = "link_stockist_brand")
public class LinkStockistBrand implements Serializable {

	private static final long serialVersionUID = -4881658361926640487L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_link_stockist_brand")
	private Long idLinkStockistBrand;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_stockist")
	private Stockist stockist;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_brand")
	private Brand brand;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Stockist getStockist() {
		return stockist;
	}

	public void setStockist(Stockist stockist) {
		this.stockist = stockist;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

	public Long getIdLinkStockistBrand() {
		return idLinkStockistBrand;
	}

	public void setIdLinkStockistBrand(Long idLinkStockistBrand) {
		this.idLinkStockistBrand = idLinkStockistBrand;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

}
