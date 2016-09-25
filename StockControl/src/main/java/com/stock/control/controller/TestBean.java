package com.stock.control.controller;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class TestBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(TestBean.class);

	private String test = "prova 123 prova";

	public String getTest() {
		return test;
	}

	public void setTest(String test) {
		this.test = test;
	}

}
