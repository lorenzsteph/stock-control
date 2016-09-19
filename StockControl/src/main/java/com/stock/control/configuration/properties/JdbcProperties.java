package com.stock.control.configuration.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JdbcProperties {

	@Value("${datasource.driverClassName}")
	private String jdbcDriver;

	@Value("${datasource.url}")
	private String jdbcUrl;

	@Value("${datasource.username}")
	private String jdbcUsername;

	@Value("${datasource.password}")
	private String jdbcPassword;

	@Value("${jdbc.default.initialSize}")
	private String jdbcInitialSize;

	@Value("${jdbc.default.maxActive}")
	private String jdbcMaxActive;

	@Value("${jdbc.default.maxIdle}")
	private String jdbcMaxIdle;

	@Value("${jdbc.default.minIdle}")
	private String jdbcMinIdle;

	@Value("${jdbc.default.defaultAutoCommit}")
	private String jdbcAutoCommit;

	@Value("${jdbc.default.removeAbandoned}")
	private String jdbcAbandoned;

	@Value("${jdbc.default.removeAbandonedTimeout}")
	private String jdbcAbandonedTimeout;

	@Value("${jdbc.default.logAbandoned}")
	private String jdbcLogAbandoned;

	@Value("${jdbc.default.validationQuery}")
	private String jdbcValidationQuery;

	@Value("${jdbc.default.testOnBorrow}")
	private String jdbcOnBorrow;

	@Value("${jdbc.default.testWhileIdle}")
	private String jdbcWhileIdle;

	@Value("${jdbc.default.timeBetweenEvictionRunsMillis}")
	private String jdbcBetweenEvictionRunsMillis;

	@Value("${jdbc.default.numTestsPerEvictionRun}")
	private String jdbcTestsPerEvictionRun;

	public String getJdbcDriver() {
		return jdbcDriver;
	}

	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getJdbcUsername() {
		return jdbcUsername;
	}

	public void setJdbcUsername(String jdbcUsername) {
		this.jdbcUsername = jdbcUsername;
	}

	public String getJdbcPassword() {
		return jdbcPassword;
	}

	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	public String getJdbcInitialSize() {
		return jdbcInitialSize;
	}

	public void setJdbcInitialSize(String jdbcInitialSize) {
		this.jdbcInitialSize = jdbcInitialSize;
	}

	public String getJdbcMaxActive() {
		return jdbcMaxActive;
	}

	public void setJdbcMaxActive(String jdbcMaxActive) {
		this.jdbcMaxActive = jdbcMaxActive;
	}

	public String getJdbcMaxIdle() {
		return jdbcMaxIdle;
	}

	public void setJdbcMaxIdle(String jdbcMaxIdle) {
		this.jdbcMaxIdle = jdbcMaxIdle;
	}

	public String getJdbcMinIdle() {
		return jdbcMinIdle;
	}

	public void setJdbcMinIdle(String jdbcMinIdle) {
		this.jdbcMinIdle = jdbcMinIdle;
	}

	public String getJdbcAutoCommit() {
		return jdbcAutoCommit;
	}

	public void setJdbcAutoCommit(String jdbcAutoCommit) {
		this.jdbcAutoCommit = jdbcAutoCommit;
	}

	public String getJdbcAbandoned() {
		return jdbcAbandoned;
	}

	public void setJdbcAbandoned(String jdbcAbandoned) {
		this.jdbcAbandoned = jdbcAbandoned;
	}

	public String getJdbcAbandonedTimeout() {
		return jdbcAbandonedTimeout;
	}

	public void setJdbcAbandonedTimeout(String jdbcAbandonedTimeout) {
		this.jdbcAbandonedTimeout = jdbcAbandonedTimeout;
	}

	public String getJdbcLogAbandoned() {
		return jdbcLogAbandoned;
	}

	public void setJdbcLogAbandoned(String jdbcLogAbandoned) {
		this.jdbcLogAbandoned = jdbcLogAbandoned;
	}

	public String getJdbcValidationQuery() {
		return jdbcValidationQuery;
	}

	public void setJdbcValidationQuery(String jdbcValidationQuery) {
		this.jdbcValidationQuery = jdbcValidationQuery;
	}

	public String getJdbcOnBorrow() {
		return jdbcOnBorrow;
	}

	public void setJdbcOnBorrow(String jdbcOnBorrow) {
		this.jdbcOnBorrow = jdbcOnBorrow;
	}

	public String getJdbcWhileIdle() {
		return jdbcWhileIdle;
	}

	public void setJdbcWhileIdle(String jdbcWhileIdle) {
		this.jdbcWhileIdle = jdbcWhileIdle;
	}

	public String getJdbcBetweenEvictionRunsMillis() {
		return jdbcBetweenEvictionRunsMillis;
	}

	public void setJdbcBetweenEvictionRunsMillis(String jdbcBetweenEvictionRunsMillis) {
		this.jdbcBetweenEvictionRunsMillis = jdbcBetweenEvictionRunsMillis;
	}

	public String getJdbcTestsPerEvictionRun() {
		return jdbcTestsPerEvictionRun;
	}

	public void setJdbcTestsPerEvictionRun(String jdbcTestsPerEvictionRun) {
		this.jdbcTestsPerEvictionRun = jdbcTestsPerEvictionRun;
	}

}