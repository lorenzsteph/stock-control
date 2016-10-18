package com.stock.control.controller.pf.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.stock.control.model.Ring;

@Component(value = "homeRing")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class HomeRingControllerBean implements Serializable {

	private static final long serialVersionUID = -4329067638789959238L;

	private static final Logger log = LoggerFactory.getLogger(HomeRingControllerBean.class);
	private List<Ring> rings;
	private Ring selectedRing;

	@PostConstruct
	public void init() {
		log.debug("init ring component");
		rings = new ArrayList<Ring>();

		rings.add(new Ring("1", "Entrate", "Black","Statistiche sulle entrate"));
		rings.add(new Ring("2", "Uscite", "Orange","Statistiche sulle uscite"));
		rings.add(new Ring("4", "Fornitori", "Red","Statistiche sui fornitori"));
		rings.add(new Ring("5", "Clienti", "Yellow","Statistiche sui clienti"));
		rings.add(new Ring("6", "Magazino", "Blue","Statistiche sul magazino"));
	
	}

	public List<Ring> getRings() {
		return rings;
	}

	public void setRings(List<Ring> rings) {
		this.rings = rings;
	}

	public Ring getSelectedRing() {
		return selectedRing;
	}

	public void setSelectedRing(Ring selectedRing) {
		this.selectedRing = selectedRing;
	}

}
