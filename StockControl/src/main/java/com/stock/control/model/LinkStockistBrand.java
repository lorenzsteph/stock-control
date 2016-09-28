package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link_stockist_brand")
public class LinkStockistBrand implements Serializable {

	private static final long serialVersionUID = -4881658361926640487L;

	@Id
	@Column(name = "id_stockist")
	private Long idStockist;

	@Column(name = "id_brand")
	private Long idBrand;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	public Long getIdStockist() {
		return idStockist;
	}

	public void setIdStockist(Long idStockist) {
		this.idStockist = idStockist;
	}

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
	}

	public Date getDateEndValidity() {
		return dateEndValidity;
	}

	public void setDateEndValidity(Date dateEndValidity) {
		this.dateEndValidity = dateEndValidity;
	}

}
