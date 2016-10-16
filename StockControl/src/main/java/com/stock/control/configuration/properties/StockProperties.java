package com.stock.control.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StockProperties {

	@Value("${example}")
	private String example;

	@Value("${path.lucene}")
	private String pathLucene;

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	public String getPathLucene() {
		return pathLucene;
	}

	public void setPathLucene(String pathLucene) {
		this.pathLucene = pathLucene;
	}

}