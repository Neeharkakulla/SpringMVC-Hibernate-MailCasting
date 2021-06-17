package com.api.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages="com.api.")
@PropertySource(value="classpath:application.properties")
public class HibernateConfig {
	@Autowired
    Environment environment;
 
    //--------------------
    private final String PROPERTY_DRIVER = "spring.datasource.driver-class-name";
	private final String PROPERTY_URL = "spring.datasource.url";
	private final String PROPERTY_USERNAME = "spring.datasource.user";
	private final String PROPERTY_PASSWORD = "spring.datasource.password";
	private final String PROPERTY_SHOW_SQL = "spring.jpa.hibernate.show_sql";
	private final String PROPERTY_DIALECT = "hibernate.dialect";
	private final String PROPERTY_DDL_AUTO="spring.jpa.hibernate.ddl-auto";


	@Bean
	DataSource dataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setUrl(environment.getProperty(PROPERTY_URL));
		ds.setUsername(environment.getProperty(PROPERTY_USERNAME));
		ds.setPassword(environment.getProperty(PROPERTY_PASSWORD));
		ds.setDriverClassName(environment.getProperty(PROPERTY_DRIVER));
		return ds;
	}

	Properties hibernateProps() {
		Properties properties = new Properties();
		properties.setProperty(PROPERTY_DIALECT, environment.getProperty(PROPERTY_DIALECT));
		properties.setProperty(PROPERTY_SHOW_SQL, environment.getProperty(PROPERTY_SHOW_SQL));
		properties.setProperty(PROPERTY_DDL_AUTO, environment.getProperty(PROPERTY_DDL_AUTO));
		return properties;
	}
	
	
	 @Bean
   public HibernateTransactionManager getTransactionManager() {
       HibernateTransactionManager transactionManager = new HibernateTransactionManager();
       transactionManager.setSessionFactory(sessionFactory().getObject());
       return transactionManager;
   }
	 
	 @Bean
   public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
       sessionFactory.setDataSource(dataSource());
       sessionFactory.setPackagesToScan(new String[] {"com.api.model"});
       sessionFactory.setHibernateProperties(hibernateProps());
       return sessionFactory;
   }
}