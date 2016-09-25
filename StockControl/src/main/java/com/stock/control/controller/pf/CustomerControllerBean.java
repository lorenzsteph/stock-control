package com.stock.control.controller.pf;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component(value="customer")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CustomerControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(CustomerControllerBean.class);

	private String test = "prova 123 prova";

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		log.debug("test");
		this.test = test;
	}

}
