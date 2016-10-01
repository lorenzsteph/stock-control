package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.model.LinkStockistBrand;
import com.stock.control.service.BrandService;

public class BrandLazyListDataModel extends LazyDataModel<LinkStockistBrand> {

	private static final long serialVersionUID = 1L;

	private BrandService brandService;
	private List<LinkStockistBrand> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public BrandLazyListDataModel(BrandService brandService, SelectedRecordBean selectedRecordBean) {
		this.brandService = brandService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<LinkStockistBrand> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<LinkStockistBrand> page = brandService.findBrandByIdStockist(selectedRecordBean.getStockist().getIdStockist());
		setRowCount(page.size());

		dataModel = page;

		return this.dataModel;
	}

	@Override
	public LinkStockistBrand getRowData(String rowKey) {
		for (LinkStockistBrand s : dataModel) {
			if (s.getBrand().getIdBrand() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(LinkStockistBrand object) {
		return object.getBrand().getIdBrand();
	}

}
