package com.example.app.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.MyJpaRepositoryFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.domain.service.OsUserAuditorAware;

/**
 * Created by ikeya on 15/09/06.
 */
@EnableTransactionManagement
@EnableJpaRepositories(value = "com.example.domain.repository",repositoryFactoryBeanClass = MyJpaRepositoryFactoryBean.class)
@EnableJpaAuditing
@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {

//    @Autowired
//    PersistenceExceptionTranslationPostProcessor pp;
    
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.H2);
        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);
        return vendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        factory.setPackagesToScan("com.example.domain.model");
        factory.setJpaVendorAdapter(jpaVendorAdapter());

        Map<String, String> propertyMap = new HashMap<>();
        // propertyMap.put("hibernate.transaction.jta.platform",
        // WeblogicJtaPlatform.class.getName());
        factory.setJpaPropertyMap(propertyMap);
        return factory;
    }

    @Bean
    public EntityManager entityManager(DataSource dataSource) {
        return entityManagerFactory(dataSource).getObject()
                .createEntityManager();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
        return jpaTransactionManager;
    }

    // @Bean
    // public PersistenceExceptionTranslationPostProcessor
    // persistenceExceptionTranslationPostProcessor() {
    // return new PersistenceExceptionTranslationPostProcessor();
    // }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/**/*.html")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.png");
    }

    @Bean
    public OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor() {
        return new OpenEntityManagerInViewInterceptor();
    }
    
    @Bean
    public AuditorAware<String> auditorAware() {
        return new OsUserAuditorAware();
    }
}
