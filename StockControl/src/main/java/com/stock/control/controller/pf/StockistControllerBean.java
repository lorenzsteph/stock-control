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

import com.stock.control.controller.pf.datamodel.StockistLazyListDataModel;
import com.stock.control.model.Stockist;
import com.stock.control.service.StockistService;

@Component(value = "stockistCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class StockistControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(StockistControllerBean.class);

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	@Autowired
	private StockistService stockistService;

	private StockistLazyListDataModel stockistDataModel;

	private Stockist selectedStockist;

	@PostConstruct
	public void initBean() {
		stockistDataModel = new StockistLazyListDataModel(stockistService);
		selectedStockist = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Stockist Selected", ((Stockist) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setStockist((Stockist) event.getObject());
		
		
	}

	public StockistLazyListDataModel getStockistDataModel() {
		return stockistDataModel;
	}

	public void setStockistDataModel(StockistLazyListDataModel stockistDataModel) {
		this.stockistDataModel = stockistDataModel;
	}

	public Stockist getSelectedStockist() {
		return selectedStockist;
	}

	public void setSelectedStockist(Stockist selectedStockist) {
		this.selectedStockist = selectedStockist;
	}

}
