package com.stock.control.controller.pf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.ToggleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.controller.pf.datamodel.StockistOrderLazyListDataModel;
import com.stock.control.model.StockistOrder;
import com.stock.control.model.StockistOrderProduct;
import com.stock.control.model.StockistOrderProductGroup;
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

	private List<StockistOrderProductGroup> listDetail;

	@PostConstruct
	public void initBean() {
		stockistOrderDataModel = new StockistOrderLazyListDataModel(stockistOrderService);
		selectedStockistOrder = null;
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Stockist Order Selected", ((StockistOrder) event.getObject()).getNote());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	public void onRowToggle(ToggleEvent event) {
		StockistOrder stockistOrder = (StockistOrder) event.getData();
		log.debug("order expansion" + stockistOrder.getIdStockistOrder());
		listDetail = new ArrayList<StockistOrderProductGroup>();
		StockistOrderProductGroup group;

		boolean foundDuplicate = false;

		for (StockistOrderProduct sop : stockistOrder.getStockistOrderProduct()) {

			for (StockistOrderProductGroup s : listDetail) {
				if (isEqualsStockistOrderProduct(sop, s)) {
					s.setAmount(s.getAmount() + 1);
					foundDuplicate = true;
					continue;
				}

			}

			if (!foundDuplicate) {
				group = new StockistOrderProductGroup();
				group.setStockistOrderProduct(sop);
				group.setAmount(1);

				listDetail.add(group);
			}
		}
	}

	private boolean isEqualsStockistOrderProduct(StockistOrderProduct sop, StockistOrderProductGroup s) {
		if (sop.getPrice() != null && s.getStockistOrderProduct().getPrice()!=null && sop.getPrice().compareTo(s.getStockistOrderProduct().getPrice()) == 0 && sop.getProduct().getCodProduct().equals(s.getStockistOrderProduct().getProduct().getCodProduct())
				&& sop.getProduct().getRange().equals(s.getStockistOrderProduct().getProduct().getRange()) && sop.getProduct().getDescr().equals(s.getStockistOrderProduct().getProduct().getDescr())) {
			return true;
		}
		return false;
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

	public List<StockistOrderProductGroup> getListDetail() {
		return listDetail;
	}

	public void setListDetail(List<StockistOrderProductGroup> listDetail) {
		this.listDetail = listDetail;
	}

}
