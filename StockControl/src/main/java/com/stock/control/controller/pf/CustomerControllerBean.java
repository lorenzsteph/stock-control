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

import com.stock.control.controller.pf.datamodel.CustomerLazyListDataModel;
import com.stock.control.model.Customer;
import com.stock.control.service.CustomerService;

@Component(value = "customerCtrl")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CustomerControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(CustomerControllerBean.class);

	@Autowired
	private CustomerService customerService;

	private CustomerLazyListDataModel customerDataModel;

	private Customer selectedCustomer;

	@PostConstruct
	public void initBean(){
		customerDataModel = new CustomerLazyListDataModel(customerService);
		selectedCustomer = null;
	}
	
	public void onRowSelect(SelectEvent event) {
		FacesMessage msg = new FacesMessage("Customer Selected", ((Customer) event.getObject()).getDescr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public CustomerLazyListDataModel getCustomerDataModel() {
		return customerDataModel;
	}

	public void setCustomerDataModel(CustomerLazyListDataModel customerDataModel) {
		this.customerDataModel = customerDataModel;
	}

	public Customer getSelectedCustomer() {
		return selectedCustomer;
	}

	public void setSelectedCustomer(Customer selectedCustomer) {
		this.selectedCustomer = selectedCustomer;
	}

}
