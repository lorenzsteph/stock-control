package com.stock.control.controller.pf;

import java.io.IOException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

@Component
public class WebApplicationVersion implements ServletContextAware {

	private static final Logger log = LoggerFactory.getLogger(WebApplicationVersion.class);

	private String CURRENT_VERSION = null;
	private ServletContext servletContext = null;

	@PostConstruct
	public void initVersion() {
		try {
			String name = "/META-INF/MANIFEST.MF";
			Properties props = new Properties();
			if (servletContext.getResourceAsStream(name) != null) {
				props.load(servletContext.getResourceAsStream(name));
			}
			CURRENT_VERSION = (String) props.get("Implementation-Version");
			log.info("Current version web : " + CURRENT_VERSION);

		} catch (IOException e) {
			log.error("Problem get version!", e);

		}
	}

	public synchronized String getVersion() {
		return CURRENT_VERSION;
	}

	@Override
	public void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

}
