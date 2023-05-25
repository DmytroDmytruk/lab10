package com.example.database;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan
@EnableTransactionManagement
public class DBConfig {	
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.database.Entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/library");
        dataSource.setUsername("root");
        dataSource.setPassword("1111");
        return dataSource;
    }   
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    private Properties hibernateProperties() {
    	Properties hibernateProperties = new Properties();   
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        //hibernateProperties.setProperty(
          //      "hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }
}


/*
@Configuration
@ComponentScan
@EnableTransactionManagement
public class DBConfig {	
	
	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("com.example.database.Entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }    
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost/library");
        dataSource.setUsername("root");
        dataSource.setPassword("1111");
        return dataSource;
    }   
    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
    private Properties hibernateProperties() {
    	Properties hibernateProperties = new Properties();   
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        //hibernateProperties.setProperty(
          //      "hibernate.hbm2ddl.auto", "update");
        return hibernateProperties;
    }
}
*/