package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.dao.dynamic.filter.LinkCategoryProductSearchFilter;
import com.stock.control.model.LinkCategoryProduct;
import com.stock.control.service.ProductService;
import com.stock.control.utils.CommonUtils;

public class ProductLazyListDataModel extends LazyDataModel<LinkCategoryProduct> {

	private static final long serialVersionUID = 1L;

	private ProductService productService;
	private List<LinkCategoryProduct> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public ProductLazyListDataModel(ProductService productService, SelectedRecordBean selectedRecordBean) {
		this.productService = productService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<LinkCategoryProduct> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		LinkCategoryProductSearchFilter filter = new LinkCategoryProductSearchFilter();
		filter.setIdCategory(selectedRecordBean.getCategory().getIdCategory());

		Page<LinkCategoryProduct> page = productService.findProductByIdCategory(filter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	@Override
	public LinkCategoryProduct getRowData(String rowKey) {
		for (LinkCategoryProduct s : dataModel) {
			if (s.getIdLinkCategoryProduct() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(LinkCategoryProduct object) {
		return object.getIdLinkCategoryProduct();
	}

}
