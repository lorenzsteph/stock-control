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

import com.stock.control.controller.pf.datamodel.CustomerOrderLazyListDataModel;
import com.stock.control.model.CustomerOrder;
import com.stock.control.service.CustomerOrderService;

@Component(value = "customerOrderCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CustomerOrderControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(CustomerOrderControllerBean.class);

	@Autowired
	private CustomerOrderService customerOrderService;

	@Autowired
	private SelectedRecordBean selectedRecordBean;

	private CustomerOrderLazyListDataModel customerOrderDataModel;

	private CustomerOrder selectedCustomerOrder;

	@PostConstruct
	public void initBean() {
		customerOrderDataModel = new CustomerOrderLazyListDataModel(customerOrderService);
		selectedCustomerOrder = null;
		selectedRecordBean.setCustomerOrder(null);
	}

	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Customer Order Selected", ((CustomerOrder) event.getObject()).getNote());
		FacesContext.getCurrentInstance().addMessage(null, msg);

		selectedRecordBean.setCustomerOrder((CustomerOrder) event.getObject());

	}

	public void deleteCustomerOrder() {
		log.debug("delete customer order: " + selectedCustomerOrder.getIdCustomerOrder());
		customerOrderService.removeCustomerOrder(selectedCustomerOrder);
	}

	public CustomerOrderLazyListDataModel getCustomerOrderDataModel() {
		return customerOrderDataModel;
	}

	public void setCustomerOrderDataModel(CustomerOrderLazyListDataModel customerOrderDataModel) {
		this.customerOrderDataModel = customerOrderDataModel;
	}

	public CustomerOrder getSelectedCustomerOrder() {
		return selectedCustomerOrder;
	}

	public void setSelectedCustomerOrder(CustomerOrder selectedCustomerOrder) {
		this.selectedCustomerOrder = selectedCustomerOrder;
	}

	public SelectedRecordBean getSelectedRecordBean() {
		return selectedRecordBean;
	}

	public void setSelectedRecordBean(SelectedRecordBean selectedRecordBean) {
		this.selectedRecordBean = selectedRecordBean;
	}

}
