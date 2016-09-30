package com.stock.control.controller.pf;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.model.Stockist;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SelectedRecordBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private Stockist stockist;

	public Stockist getStockist() {
		return stockist;
	}

	public void setStockist(Stockist stockist) {
		this.stockist = stockist;
	}

}
