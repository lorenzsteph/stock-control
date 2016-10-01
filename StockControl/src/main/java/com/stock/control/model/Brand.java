package com.stock.control.model;

import java.io.Serializable;
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
@Table(name = "brand")
public class Brand implements Serializable {

	private static final long serialVersionUID = 3943420746709258858L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "id_brand")
	private Long idBrand;

	@Column(name = "descr")
	private String descr;

	@Column(name = "date_end_validity")
	private Date dateEndValidity;

	@OneToMany(mappedBy = "brand", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkStockistBrand> linkStockistBrand;

	@OneToMany(mappedBy = "brand", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private Set<LinkBrandCategory> linkBrandCategory;

	public Set<LinkStockistBrand> getLinkStockistBrand() {
		return linkStockistBrand;
	}

	public void setLinkStockistBrand(Set<LinkStockistBrand> linkStockistBrand) {
		this.linkStockistBrand = linkStockistBrand;
	}

	public Set<LinkBrandCategory> getLinkBrandCategory() {
		return linkBrandCategory;
	}

	public void setLinkBrandCategory(Set<LinkBrandCategory> linkBrandCategory) {
		this.linkBrandCategory = linkBrandCategory;
	}

	public Long getIdBrand() {
		return idBrand;
	}

	public void setIdBrand(Long idBrand) {
		this.idBrand = idBrand;
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

}
