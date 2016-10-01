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

import com.stock.control.controller.pf.datamodel.ProductLazyListDataModel;
import com.stock.control.model.LinkCategoryProduct;
import com.stock.control.service.ProductService;

@Component(value = "productCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ProductControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(ProductControllerBean.class);

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private ProductService productService;

	private ProductLazyListDataModel productDataModel;

	private LinkCategoryProduct selectedLinkCategoryProduct;

	@PostConstruct
	public void initBean() {
		productDataModel = new ProductLazyListDataModel(productService, selectedRecordBean);
		selectedLinkCategoryProduct = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Product Selected", (((LinkCategoryProduct) event.getObject()).getProduct()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setProduct(((LinkCategoryProduct) event.getObject()).getProduct());
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public ProductLazyListDataModel getProductDataModel() {
		return productDataModel;
	}

	public void setProductDataModel(ProductLazyListDataModel productDataModel) {
		this.productDataModel = productDataModel;
	}

	public LinkCategoryProduct getSelectedLinkCategoryProduct() {
		return selectedLinkCategoryProduct;
	}

	public void setSelectedLinkCategoryProduct(LinkCategoryProduct selectedLinkCategoryProduct) {
		this.selectedLinkCategoryProduct = selectedLinkCategoryProduct;
	}

}
