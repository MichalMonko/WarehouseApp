package com.warchlak.persistence.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig
{
	private final Environment environment;
	
	@Autowired
	public DataSourceConfig(Environment environment)
	{
		this.environment = environment;
	}
	
	@Bean
	public DataSource dataSource() throws PropertyVetoException
	{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		
		dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
		dataSource.setUser(environment.getProperty("jdbc.username"));
		dataSource.setPassword(environment.getProperty("jdbc.password"));
		
		String maxPoolSizeProperty = environment.getProperty("jdbc.maxPoolSize");
		String minPoolSizeProperty = environment.getProperty("jdbc.minPoolSize");
		String startPoolSizeProperty = environment.getProperty("jdbc.startPoolSize");
		
		int maxPoolSize = (maxPoolSizeProperty != null) ? Integer.parseInt(maxPoolSizeProperty) : 1000;
		int minPoolSize = (minPoolSizeProperty != null) ? Integer.parseInt(minPoolSizeProperty) : 10;
		int startPoolSize = (startPoolSizeProperty != null) ? Integer.parseInt(startPoolSizeProperty) : 10;
		
		dataSource.setMaxPoolSize(maxPoolSize);
		dataSource.setMinPoolSize(minPoolSize);
		dataSource.setInitialPoolSize(startPoolSize);
		
		dataSource.setDriverClass(environment.getProperty("jdbc.driver.class"));
		
		return dataSource;
	}
}
