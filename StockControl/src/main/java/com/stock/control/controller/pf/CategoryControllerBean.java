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

import com.stock.control.controller.pf.datamodel.CategoryLazyListDataModel;
import com.stock.control.model.Category;
import com.stock.control.service.CategoryService;

@Component(value = "categoryCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CategoryControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CategoryControllerBean.class);

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private CategoryService categoryService;

	private CategoryLazyListDataModel categoryDataModel;

	private Category selectedCategory;

	@PostConstruct
	public void initBean() {
		categoryDataModel = new CategoryLazyListDataModel(categoryService, selectedRecordBean);
		selectedCategory = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Category Selected", ((Category) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setCategory((Category) event.getObject());
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public CategoryLazyListDataModel getCategoryDataModel() {
		return categoryDataModel;
	}

	public void setCategoryDataModel(CategoryLazyListDataModel categoryDataModel) {
		this.categoryDataModel = categoryDataModel;
	}

	public Category getSelectedCategory() {
		return selectedCategory;
	}

	public void setSelectedCategory(Category selectedCategory) {
		this.selectedCategory = selectedCategory;
	}

}
