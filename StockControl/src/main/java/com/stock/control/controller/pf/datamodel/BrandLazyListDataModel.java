package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.model.Brand;
import com.stock.control.service.BrandService;

public class BrandLazyListDataModel extends LazyDataModel<Brand> {

	private static final long serialVersionUID = 1L;

	private BrandService brandService;
	private List<Brand> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public BrandLazyListDataModel(BrandService brandService, SelectedRecordBean selectedRecordBean) {
		this.brandService = brandService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<Brand> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<Brand> page = brandService.findBrandByIdStockist(selectedRecordBean.getStockist().getIdStockist());
		setRowCount(page.size());

		dataModel = page;

		return this.dataModel;
	}

	@Override
	public Brand getRowData(String rowKey) {
		for (Brand s : dataModel) {
			if (s.getIdBrand() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Brand object) {
		return object.getIdBrand();
	}

}
