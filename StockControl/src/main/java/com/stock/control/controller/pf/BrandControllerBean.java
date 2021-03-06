package com.stock.control.controller.pf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.BrandLazyListDataModel;
import com.stock.control.model.Brand;
import com.stock.control.service.BrandService;

@Component(value = "brandCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class BrandControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(BrandControllerBean.class);

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private BrandService brandService;

	private BrandLazyListDataModel brandDataModel;

	private Brand selectedBrand;

	@PostConstruct
	public void initBean() {
		brandDataModel = new BrandLazyListDataModel(brandService, selectedRecordBean);
		selectedBrand = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Brand Selected", ((Brand) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setBrand((Brand) event.getObject());
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

	public BrandService getBrandService() {
		return brandService;
	}

	public void setBrandService(BrandService brandService) {
		this.brandService = brandService;
	}

	public BrandLazyListDataModel getBrandDataModel() {
		return brandDataModel;
	}

	public void setBrandDataModel(BrandLazyListDataModel brandDataModel) {
		this.brandDataModel = brandDataModel;
	}

	public Brand getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(Brand selectedBrand) {
		this.selectedBrand = selectedBrand;
	}

}
