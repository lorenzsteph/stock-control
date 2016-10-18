package com.stock.control.controller.pf.home;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component(value = "homeDetail")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class HomeDetailControllerBean implements Serializable {

	private static final long serialVersionUID = -2255430055122747825L;

	private DashboardModel model;
	private List<Task> tasks;
	private Date date;
	private String txt1;

	@PostConstruct
	public void init() {
		model = new DefaultDashboardModel();
		DashboardColumn column1 = new DefaultDashboardColumn();
		DashboardColumn column2 = new DefaultDashboardColumn();
		DashboardColumn column3 = new DefaultDashboardColumn();

		column1.addWidget("todo");

		column2.addWidget("tasks");
		column3.addWidget("deadlines");

		model.addColumn(column1);
		model.addColumn(column2);
		model.addColumn(column3);
	}

	public void handleReorder(DashboardReorderEvent event) {
		FacesMessage message = new FacesMessage();
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("Reordered: " + event.getWidgetId());
		message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());

		addMessage(message);
	}

	public void handleClose(CloseEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");

		addMessage(message);
	}

	public void handleToggle(ToggleEvent event) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());

		addMessage(message);
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public DashboardModel getModel() {
		return model;
	}

	public Integer getProgress1() {
		int progress = (int) (Math.random() * 100);
		return progress;
	}

	public Integer getProgress2() {
		int progress = (int) (Math.random() * 100);
		return progress;
	}

	public Integer getProgress3() {
		int progress = (int) (Math.random() * 100);
		return progress;
	}

	public Integer getProgress4() {
		int progress = (int) (Math.random() * 100);
		return progress;
	}

	public Integer getProgress5() {
		int progress = (int) (Math.random() * 100);
		return progress;
	}

	String[] nomi = { "Massimiliano", "Francesco", "Daniele", "Manuel", "Katia", "Alessandro", "Giuseppe", "Ernesto", "Mario", "Marco" };
	String[] title = { "Nuovi asset", "Scadenzario clientela", "Anagrafiche clienti", "Ripristino asset", "Contabilità" };
	String[] text = { "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis sit amet aliquet sapien. In suscipit dolor sed est lobortis placerat.", "Cras iaculis massa et ex volutpat bibendum. Curabitur vestibulum tellus eu varius blandit.",
			"Sed magna ante, consectetur id malesuada eget, pellentesque quis elit. Ut pretium urna sit amet ornare bibendum.",
			"Maecenas cursus nunc sed semper mattis. Sed fermentum bibendum eros feugiat viverra. Aliquam varius, diam eu rhoncus ullamcorper.",
			"Suspendisse eu diam eu nisi condimentum ultricies. Nullam in laoreet libero, vel venenatis orci. Quisque cursus lobortis purus a accumsan." };
	String[] role = { "Amministratore", "Quadro", "Delegato", "Dirigente", "Interno" };
	String[] phone = { "3286325985", "3472356948", "3407854269", "3284598326", "3334278953" };
	boolean[] fave = { true, false };
	Long[] dateEvent = { 1478732400000L, 1477432800000L, 1478300400000L, 1478732400000L, 1479423600000L };

	public List<Task> getTasks() {
		if (tasks == null) {
			Task element;
			tasks = new ArrayList<>();
			for (int i = 0; i < 30; i++) {
				element = new Task(nomi[(int) (Math.random() * 10)], "" + (int) (Math.random() * 100) + "%");
				element.setTaskTitle(title[(int) (Math.random() * 5)]);
				element.setTaskNumber(Integer.toString((int) (Math.random() * 20) + 1));
				element.setDate(new Date(dateEvent[(int) (Math.random() * 5)]));
				element.setDeadlinesText(text[(int) (Math.random() * 5)]);
				element.setContactRole(role[(int) (Math.random() * 5)]);
				element.setContactPhone(phone[(int) (Math.random() * 5)]);
				element.setContactFave(fave[(int) (Math.random() * 2)]);
				tasks.add(element);
			}
		}
		return tasks;
	}

	public List<Task> getContacts() {
		Task element;
		ArrayList<Task> contacts = new ArrayList<>();
		for (int i = 0; i < 30; i++) {
			String name2Search = nomi[(int) (Math.random() * 10)];
			if (txt1 != null && name2Search.toUpperCase().indexOf(txt1.toUpperCase()) == -1)
				continue;

			element = new Task(name2Search, "" + (int) (Math.random() * 100) + "%");
			element.setContactRole(role[(int) (Math.random() * 5)]);
			element.setContactPhone(phone[(int) (Math.random() * 5)]);
			element.setContactFave(fave[(int) (Math.random() * 2)]);
			contacts.add(element);
		}
		return contacts;
	}

	public void setTask(List<Task> tasks) {
		this.tasks = tasks;
	}

	public void buttonAction(ActionEvent actionEvent) {
		addMessage("Welcome to Primefaces!!");
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<String> completeText(String query) {
		if (query == null)
			return null;
		List<String> results = new ArrayList<>();
		for (String nomi1 : nomi) {
			if (nomi1.toUpperCase().indexOf(query.toUpperCase()) == -1)
				continue;

			results.add(nomi1);
		}

		return results;
	}

	public String getTxt1() {
		return txt1;
	}

	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}

}
