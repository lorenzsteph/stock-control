package com.stock.control.dao.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.stock.control.configuration.properties.JdbcProperties;

@Component
public class DatabaseDaoInitializer implements InitializingBean {

	private static final Logger log = LoggerFactory.getLogger(DatabaseDaoInitializer.class);

	@Autowired
	private JdbcProperties jdbcProperties;

	private NamedParameterJdbcTemplate namedParameterjdbcTemplate;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("----> DatabaseDaoAbstrac afterPropertiesSet ");
		DataSource dataSource = new DataSource();

		dataSource.setDriverClassName(jdbcProperties.getJdbcDriver());
		dataSource.setUrl(jdbcProperties.getJdbcUrl());
		dataSource.setUsername(jdbcProperties.getJdbcUsername());
		dataSource.setPassword(jdbcProperties.getJdbcPassword());

		dataSource.setInitialSize(Integer.parseInt(jdbcProperties.getJdbcInitialSize()));
		dataSource.setMaxActive(Integer.parseInt(jdbcProperties.getJdbcMaxActive()));
		dataSource.setMaxIdle(Integer.parseInt(jdbcProperties.getJdbcMaxIdle()));
		dataSource.setMinIdle(Integer.parseInt(jdbcProperties.getJdbcMinIdle()));

		dataSource.setDefaultAutoCommit(Boolean.valueOf(jdbcProperties.getJdbcAutoCommit()));
		dataSource.setRemoveAbandoned(Boolean.valueOf(jdbcProperties.getJdbcAbandoned()));
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(jdbcProperties.getJdbcAbandonedTimeout()));
		dataSource.setLogAbandoned(Boolean.valueOf(jdbcProperties.getJdbcLogAbandoned()));
		dataSource.setValidationQuery(jdbcProperties.getJdbcValidationQuery());
		dataSource.setTestOnBorrow(Boolean.valueOf(jdbcProperties.getJdbcOnBorrow()));
		dataSource.setTestWhileIdle(Boolean.valueOf(jdbcProperties.getJdbcWhileIdle()));
		dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(jdbcProperties.getJdbcBetweenEvictionRunsMillis()));
		dataSource.setNumTestsPerEvictionRun(Integer.parseInt(jdbcProperties.getJdbcTestsPerEvictionRun()));

		namedParameterjdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		log.debug("##End create datasource ");
	}

	public NamedParameterJdbcTemplate getNamedParameterjdbcTemplate() {
		return namedParameterjdbcTemplate;
	}
}
