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

import com.stock.control.controller.pf.datamodel.RangeLazyListDataModel;
import com.stock.control.model.LinkProductRange;
import com.stock.control.service.RangeService;

@Component(value = "rangeCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class RangeControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(RangeControllerBean.class);

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private RangeService rangeService;

	private RangeLazyListDataModel rangeDataModel;

	private LinkProductRange selectedLinkProductRange;

	@PostConstruct
	public void initBean() {
		rangeDataModel = new RangeLazyListDataModel(rangeService, selectedRecordBean);
		setSelectedLinkProductRange(null);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Range Selected", (((LinkProductRange) event.getObject()).getRange()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setRange(((LinkProductRange) event.getObject()).getRange());
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

	public RangeService getRangeService() {
		return rangeService;
	}

	public void setRangeService(RangeService rangeService) {
		this.rangeService = rangeService;
	}

	public RangeLazyListDataModel getRangeDataModel() {
		return rangeDataModel;
	}

	public void setRangeDataModel(RangeLazyListDataModel rangeDataModel) {
		this.rangeDataModel = rangeDataModel;
	}

	public LinkProductRange getSelectedLinkProductRange() {
		return selectedLinkProductRange;
	}

	public void setSelectedLinkProductRange(LinkProductRange selectedLinkProductRange) {
		this.selectedLinkProductRange = selectedLinkProductRange;
	}

}
