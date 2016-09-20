package com.stock.control.dao.util;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractDaoBean extends NamedParameterJdbcDaoSupport {

	@Resource(name = "dataSourceStock")
	private DataSource ds;

	@PostConstruct
	public void init() {
		setDataSource(ds);
	}

}
