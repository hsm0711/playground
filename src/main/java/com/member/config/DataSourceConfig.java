package com.member.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {
	private final String MASTER_DATA_SOURCE = "masterDataSource";
	private final String SLAVE_DATA_SOURCE = "slaveDataSource";

	@Primary
	@Bean(MASTER_DATA_SOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.master.hikari")
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(SLAVE_DATA_SOURCE)
	@ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
}
