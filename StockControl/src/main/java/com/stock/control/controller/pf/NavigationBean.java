package com.stock.control.controller.pf;

import java.io.Serializable;

import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.context.FacesContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(WebApplicationContext.SCOPE_APPLICATION)
public class NavigationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	public void goToPageSelected(String pageUrl) {

		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		configurableNavigationHandler.performNavigation(pageUrl);

	}
}
