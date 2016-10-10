package com.stock.control.controller.pf;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.ProductLazyListDataModel;
import com.stock.control.model.Product;
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

	private Product selectedProduct;

	@PostConstruct
	public void initBean() {
		productDataModel = new ProductLazyListDataModel(productService, selectedRecordBean);
		selectedProduct = null;
	}

	public void deleteProduct() {
		productService.deleteProduct(selectedProduct.getIdProduct());
		FacesMessage msg = new FacesMessage("Product deleted");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void addProduct() {
		Product newProduct = new Product();
		newProduct.setCodProduct(" ");
		newProduct.setBrand(selectedRecordBean.getBrand());
		newProduct.setCategory(selectedRecordBean.getCategory());
		newProduct = productService.saveProduct(newProduct);
		FacesMessage msg = new FacesMessage("Product added id : " + newProduct.getIdProduct());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Product Selected", ((Product) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setProduct((Product) event.getObject());
	}

	public void onRowEdit(RowEditEvent event) {
		Product productEdit = (Product) event.getObject();
		if (productEdit.getCodProduct() != null) {
			productEdit.setCodProduct(productEdit.getCodProduct().trim());
		}
		productEdit = productService.saveProduct(productEdit);

		FacesMessage msg = new FacesMessage("Product Edited", productEdit.getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((Product) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
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

	public Product getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(Product selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

}
