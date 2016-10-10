package com.stock.control.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "stockist")
public class Stockist implements Serializable {

	private static final long serialVersionUID = -5706970871079524197L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_stockist")
	private Long idStockist;

	@Column(name = "descr")
	private String descr;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	@OneToMany(mappedBy = "stockist", fetch = FetchType.EAGER)
	private Set<Brand> brand;

	@OneToMany(mappedBy = "stockist", fetch = FetchType.EAGER)
	private Set<StockistOrder> stockistOrder;

	public Long getIdStockist() {
		return idStockist;
	}

	public void setIdStockist(Long idStockist) {
		this.idStockist = idStockist;
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

	public Set<Brand> getBrand() {
		return brand;
	}

	public void setBrand(Set<Brand> brand) {
		this.brand = brand;
	}

	public Set<StockistOrder> getStockistOrder() {
		return stockistOrder;
	}

	public void setStockistOrder(Set<StockistOrder> stockistOrder) {
		this.stockistOrder = stockistOrder;
	}

}
