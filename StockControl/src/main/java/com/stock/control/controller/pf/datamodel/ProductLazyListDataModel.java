package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.dao.dynamic.filter.ProductSearchFilter;
import com.stock.control.model.Product;
import com.stock.control.service.ProductService;
import com.stock.control.utils.CommonUtils;

public class ProductLazyListDataModel extends LazyDataModel<Product> {

	private static final long serialVersionUID = 1L;

	private ProductService productService;
	private List<Product> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public ProductLazyListDataModel(ProductService productService, SelectedRecordBean selectedRecordBean) {
		this.productService = productService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<Product> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		ProductSearchFilter filter = new ProductSearchFilter();
		filter.setIdCategory(selectedRecordBean.getCategory().getIdCategory());

		Page<Product> page = productService.findProductByIdCategory(filter, CommonUtils.getPageNumber(first, pageSize), pageSize);
		setRowCount(new Long(page.getTotalElements()).intValue());

		dataModel = page.getContent();

		return this.dataModel;
	}

	@Override
	public Product getRowData(String rowKey) {
		for (Product s : dataModel) {
			if (s.getIdProduct() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(Product object) {
		return object.getIdProduct();
	}

}
