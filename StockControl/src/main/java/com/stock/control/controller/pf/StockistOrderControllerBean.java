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

import com.stock.control.controller.pf.datamodel.StockistOrderLazyListDataModel;
import com.stock.control.model.StockistOrder;
import com.stock.control.service.StockistOrderService;

@Component(value = "stockistOrderCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StockistOrderControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(StockistOrderControllerBean.class);

	@Autowired
	private StockistOrderService stockistOrderService;

	private StockistOrderLazyListDataModel stockistOrderDataModel;

	private StockistOrder selectedStockistOrder;

	@PostConstruct
	public void initBean() {
		stockistOrderDataModel = new StockistOrderLazyListDataModel(stockistOrderService);
		selectedStockistOrder = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Stockist Order Selected", ((StockistOrder) event.getObject()).getNote());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void deleteStockistOrder() {
		log.debug("delete Stockist order: " + selectedStockistOrder.getIdStockistOrder());
		stockistOrderService.removeStockistOrder(selectedStockistOrder);
	}

	public StockistOrderLazyListDataModel getStockistOrderDataModel() {
		return stockistOrderDataModel;
	}

	public void setStockistOrderDataModel(StockistOrderLazyListDataModel stockistOrderDataModel) {
		this.stockistOrderDataModel = stockistOrderDataModel;
	}

	public StockistOrder getSelectedStockistOrder() {
		return selectedStockistOrder;
	}

	public void setSelectedStockistOrder(StockistOrder selectedStockistOrder) {
		this.selectedStockistOrder = selectedStockistOrder;
	}

}
