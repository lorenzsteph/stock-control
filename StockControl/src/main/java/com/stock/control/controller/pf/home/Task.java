/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stock.control.controller.pf.home;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {

	private static final long serialVersionUID = -6695532109681334157L;

	private String name;
	private String ratio;
	private String taskTitle;
	private String taskNumber;
	private Date date;
	private String deadlinesText;
	private String contactRole;
	private String contactMail;
	private String contactPhone;
	private boolean contactFave;

	public Task(String name, String ratio) {
		this.name = name;
		this.ratio = ratio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDeadlinesText() {
		return deadlinesText;
	}

	public void setDeadlinesText(String deadlinesText) {
		this.deadlinesText = deadlinesText;
	}

	public String getContactRole() {
		return contactRole;
	}

	public void setContactRole(String contactRole) {
		this.contactRole = contactRole;
	}

	public String getContactMail() {
		return contactMail;
	}

	public void setContactMail(String contactMail) {
		this.contactMail = contactMail;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public boolean getContactFave() {
		return contactFave;
	}

	public void setContactFave(boolean contactFave) {
		this.contactFave = contactFave;
	}

}
