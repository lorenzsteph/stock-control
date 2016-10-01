package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.model.LinkBrandCategory;
import com.stock.control.service.CategoryService;

public class CategoryLazyListDataModel extends LazyDataModel<LinkBrandCategory> {

	private static final long serialVersionUID = 1L;

	private CategoryService categoryService;
	private List<LinkBrandCategory> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public CategoryLazyListDataModel(CategoryService categoryService, SelectedRecordBean selectedRecordBean) {
		this.categoryService = categoryService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<LinkBrandCategory> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<LinkBrandCategory> page = categoryService.findCategoryByIdBrand(selectedRecordBean.getBrand().getIdBrand());
		setRowCount(page.size());

		dataModel = page;

		return this.dataModel;
	}

	@Override
	public LinkBrandCategory getRowData(String rowKey) {
		for (LinkBrandCategory s : dataModel) {
			if (s.getCategory().getIdCategory() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(LinkBrandCategory object) {
		return object.getCategory().getIdCategory();
	}

}
