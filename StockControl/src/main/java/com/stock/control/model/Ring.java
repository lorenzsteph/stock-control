package com.stock.control.model;

import java.io.Serializable;

public class Ring implements Serializable {

	private static final long serialVersionUID = -4652951600994096526L;

	private String position;
	private String info;
	private String color;
	private String descr;

	public Ring(String position, String info, String color, String descr) {
		this.position = position;
		this.info = info;
		this.color = color;
		this.descr = descr;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

}
