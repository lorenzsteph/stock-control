package com.stock.control.controller.pf.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.stock.control.controller.pf.SelectedRecordBean;
import com.stock.control.model.LinkProductRange;
import com.stock.control.service.RangeService;

public class RangeLazyListDataModel extends LazyDataModel<LinkProductRange> {

	private static final long serialVersionUID = 1L;

	private RangeService rangeService;
	private List<LinkProductRange> dataModel;

	private SelectedRecordBean selectedRecordBean;

	public RangeLazyListDataModel(RangeService rangeService, SelectedRecordBean selectedRecordBean) {
		this.rangeService = rangeService;
		this.selectedRecordBean = selectedRecordBean;
	}

	@Override
	public List<LinkProductRange> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		List<LinkProductRange> page = rangeService.findRangeByIdProduct(selectedRecordBean.getProduct().getIdProduct());
		setRowCount(page.size());

		dataModel = page;

		return this.dataModel;
	}

	@Override
	public LinkProductRange getRowData(String rowKey) {
		for (LinkProductRange s : dataModel) {
			if (s.getRange().getIdRange() == Long.parseLong(rowKey)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public Object getRowKey(LinkProductRange object) {
		return object.getRange().getIdRange();
	}

}
