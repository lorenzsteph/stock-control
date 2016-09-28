package com.stock.control.configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class StockControlInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(JPAConfiguration.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherServlet = new AnnotationConfigWebApplicationContext();
		dispatcherServlet.register(MvcConfiguration.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(dispatcherServlet));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/services/");

		ServletRegistration.Dynamic primefaces = container.addServlet("Faces Servlet", new FacesServlet());
		primefaces.setLoadOnStartup(2);
		primefaces.addMapping("*.xhtml");

		container.setInitParameter("javax.faces.FACELETS_DEVELOPMENT", "true");
		container.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", "true");
		container.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "0");
		container.setInitParameter("primefaces.THEME", "adamantium");
		container.setInitParameter("javax.faces.FACELETS_LIBRARIES", "/WEB-INF/primefaces-adamantium.taglib.xml");
	}

}