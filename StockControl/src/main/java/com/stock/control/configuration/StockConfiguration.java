package com.stock.control.configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.stock.control.configuration.properties.JdbcProperties;

@Configuration
@ComponentScan(basePackages = "com.stock.control")
@EnableWebMvc
@EnableTransactionManagement
@PropertySources({ @PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = false), @PropertySource(value = "classpath:stock.properties", ignoreResourceNotFound = false) })
public class StockConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	private JdbcProperties jdbcProperties;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
		registry.addResourceHandler("/stock/**").addResourceLocations("/stock/");
	}

	@Bean
	public DataSourceTransactionManager stockTransactionManager() {
		return new DataSourceTransactionManager(dataSourceStock());
	}

	@Bean
	public DataSource dataSourceStock() {
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
		
		return dataSource;
	}
}
