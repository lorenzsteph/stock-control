package com.stock.control.configuration;

import javax.persistence.EntityManagerFactory;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.stock.control.configuration.properties.JdbcProperties;

@Configuration
@ComponentScan(basePackages = "com.stock.control")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.stock.control.dao")
@PropertySources({ @PropertySource(value = "classpath:jdbc.properties", ignoreResourceNotFound = false), @PropertySource(value = "classpath:stock.properties", ignoreResourceNotFound = false) })
@EnableScheduling
@EnableAsync
public class JPAConfiguration {

	@Autowired
	private JdbcProperties jdbcProperties;

	@Bean
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan("com.stock.control.model");
		factory.setDataSource(dataSourceStock());
		factory.afterPropertiesSet();
		return factory.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
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
