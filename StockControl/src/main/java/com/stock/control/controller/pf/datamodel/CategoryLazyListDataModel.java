package com.stock.control.controller.pf.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.model.Category;
import com.stock.control.service.CategoryService;

public class CategoryLazyListDataModel extends LazyDataModel<Category> {

	private static final long serialVersionUID = 1L;

	private CategoryService categoryService;
	private List<Category> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public CategoryLazyListDataModel(CategoryService categoryService, SelectedRecordBean selectedRecordBean) {
		this.categoryService = categoryService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<Category> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		if (selectedRecordBean.getBrand() != null) {
			List<Category> page = categoryService.findCategoryByIdBrand(selectedRecordBean.getBrand().getIdBrand());
			setRowCount(page.size());
			List<Category> result = new ArrayList<Category>();
			for (int i = 0; i < page.size(); i++) {
				if (i < first || i > first + pageSize) {
					continue;
				}
				result.add(page.get(i));

			}

			dataModel = result;
		}

		return this.dataModel;
	}

	@Override
	public Category getRowData(String rowKey) {
		for (Category s : dataModel) {
			if (s.getIdCategory() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Category object) {
		return object.getIdCategory();
	}

}
