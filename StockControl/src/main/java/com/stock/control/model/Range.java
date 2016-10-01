package com.stock.control.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "range")
public class Range implements Serializable {

	private static final long serialVersionUID = -8598214084832322863L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_range")
	private Long idRange;

	@Column(name = "color")
	private String color;

	@Column(name = "selling_price")
	private BigDecimal sellingPrice;

	@Column(name = "descr")
	private String descr;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	@OneToMany(mappedBy = "range", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkProductRange> linkProductRange;

	public Long getIdRange() {
		return idRange;
	}

	public void setIdRange(Long idRange) {
		this.idRange = idRange;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
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

	public Set<LinkProductRange> getLinkProductRange() {
		return linkProductRange;
	}

	public void setLinkProductRange(Set<LinkProductRange> linkProductRange) {
		this.linkProductRange = linkProductRange;
	}

}
