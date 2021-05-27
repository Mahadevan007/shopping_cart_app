package com.example.cart;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com"})
@PropertySource(value= {"classpath:application.properties"})
public class HibernateConfiguration {

	@Autowired
	private Environment environment;
	
	private Properties getDBProperties() {
		Properties prop=new Properties();
		prop.put("hibernate.connection.username", 
					environment.getRequiredProperty("spring.datasource.username"));
		prop.put("hibernate.connection.password", 
				environment.getRequiredProperty("spring.datasource.password"));
		prop.put("hibernate.dialect",environment.getRequiredProperty("spring.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getRequiredProperty("spring.jpa.show-sql"));
		prop.setProperty("hibernate.hbm2ddl.auto",environment.getRequiredProperty("spring.jpa.hibernate.ddl-auto"));
		return prop;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setUsername(environment.getRequiredProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getRequiredProperty("spring.datasource.password"));
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));		
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		return dataSource;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setHibernateProperties(getDBProperties());
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.example.cart.model"});
		return sessionFactory;
	}
}








