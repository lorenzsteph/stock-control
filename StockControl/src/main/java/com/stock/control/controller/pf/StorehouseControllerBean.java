package com.stock.control.controller.pf;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.StorehouseLazyListDataModel;
import com.stock.control.model.Category;
import com.stock.control.model.Storehouse;
import com.stock.control.service.CategoryService;
import com.stock.control.service.StorehouseService;

@Component(value = "storehouseCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StorehouseControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(StorehouseControllerBean.class);

	@Autowired
	private StorehouseService storehouseService;

	@Autowired
	private CategoryService categoryService;

	private StorehouseLazyListDataModel storehouseDataModel;

	private Storehouse selectedStorehouse;

	private List<Category> categrys;

	@PostConstruct
	public void initBean() {
		storehouseDataModel = new StorehouseLazyListDataModel(storehouseService);
		selectedStorehouse = null;
		categrys = categoryService.findAllCategory();
	}

	public List<Category> getCategrys() {
		return categrys;
	}

	public void setCategrys(List<Category> categrys) {
		this.categrys = categrys;
	}

	public StorehouseService getStorehouseService() {
		return storehouseService;
	}

	public void setStorehouseService(StorehouseService storehouseService) {
		this.storehouseService = storehouseService;
	}

	public StorehouseLazyListDataModel getStorehouseDataModel() {
		return storehouseDataModel;
	}

	public void setStorehouseDataModel(StorehouseLazyListDataModel storehouseDataModel) {
		this.storehouseDataModel = storehouseDataModel;
	}

	public Storehouse getSelectedStorehouse() {
		return selectedStorehouse;
	}

	public void setSelectedStorehouse(Storehouse selectedStorehouse) {
		this.selectedStorehouse = selectedStorehouse;
	}

}
