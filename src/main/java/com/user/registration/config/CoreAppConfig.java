package com.user.registration.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.user.registration.SystemProperties;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.user.registration")
@PropertySource(name = "env", value = "classpath:app-config.properties")
public class CoreAppConfig {

    @Autowired
    private Environment env;

    @Bean
    public SystemProperties props() {
        return new SystemProperties(env);
    }
    
    @Bean
    public ObjectMapper objectMapper() {
    	return new ObjectMapper().registerModule(new JodaModule());
    }

    @Bean
    public AnnotationSessionFactoryBean sessionFactoryBean() {
        AnnotationSessionFactoryBean factory = new AnnotationSessionFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPackagesToScan("com.user.registration.domain");
        factory.setHibernateProperties(hibernateProperties());
        return factory;
    }
    
    private Properties hibernateProperties() {
        return new Properties() {
            {
                setProperty("hibernate.hbm2ddl.auto", "create");
                setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
                setProperty("hibernate.show_sql", "true");
            }
        };
    }
    
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.HSQL)
//            .addScript("classpath:schema.sql")
//            .addScript("classpath:test-data.sql")
            .build();
    }

    @Bean
    public SessionFactory sessionFactory() {
       return (SessionFactory) sessionFactoryBean().getObject();
    }
    
    @Bean
    public HibernateTransactionManager annotationDrivenTransactionManager() {
      return new HibernateTransactionManager(sessionFactory());
    }
        
}
