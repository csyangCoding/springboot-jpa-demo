package com.csy.springboot.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class DbConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DbConfig.class); 
	static final String PACKAGE = "com.csy.springboot.mapper";

	@Value("${spring.datasource.druid.url}")
	private String dbUrl;

	@Value("${spring.datasource.druid.username}")
	private String username;

	@Value("${spring.datasource.druid.password}")
	private String password;

	@Value("${spring.datasource.druid.driver-class-name}")
	private String driverClassName;

	@Value("${spring.datasource.type}")
	private String dbType;

	@Value("${spring.datasource.druid.initial-size}")
	private int initialSize;

	@Value("${spring.datasource.druid.min-idle}")
	private int minIdle;

	@Value("${spring.datasource.druid.max-active}")
	private int maxActive;

	@Value("${spring.datasource.druid.max-wait}")
	private int maxWait;

	@Value("${spring.datasource.druid.time-between-eviction-runs-millis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.druid.min-evictable-idle-time-millis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.druid.validation-query}")
	private String validationQuery;

	@Value("${spring.datasource.druid.test-while-idle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.druid.test-on-borrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.druid.test-on-return}")
	private boolean testOnReturn;

	@Value("${spring.datasource.druid.pool-prepared-statements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.druid.max-pool-prepared-statement-per-connection-size}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.druid.filters}")
	private String filters;

	@Value("${spring.datasource.druid.connection-properties}")
	private String connectionProperties;

	@Value("${spring.datasource.druid.password-callback}")
	private String passwordCallbackClassName;

	@Bean(name = "dataSource")
	@Primary
	public DataSource dataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(dbUrl);
		datasource.setUsername(username);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);
		datasource.setInitialSize(initialSize);
		datasource.setMinIdle(minIdle);
		datasource.setMaxActive(maxActive);
		datasource.setMaxWait(maxWait);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
		datasource.setConnectionProperties(connectionProperties);
		datasource.setDbType(dbType);
		try {
			datasource.setPasswordCallbackClassName(passwordCallbackClassName);
		} catch (Exception e) {
			LOGGER.error("druid configuration initialization passwordCallbackClassName", e);
		}
		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			LOGGER.error("druid configuration initialization filter", e);
		}
		return datasource;
	}

	@Bean(name = "transactionManager")
	@Primary
	public DataSourceTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

}
