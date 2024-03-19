package pl.jlabs.example;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@PropertySource("classpath:application.properties")
@EnableJpaRepositories("pl.jlabs.example.repositories")
@EnableTransactionManagement
public class JpaConfig {
    @Value("${database.url}") private String url;
    @Value("${database.username}") private String username;
    @Value("${database.password}") private String password;
    @Value("${database.schema}") private String schema;

    @Bean
    DataSource dataSource() {
        var driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        // Connection params
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        driver.setSchema(schema);
        return driver;
    }

    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        var factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource);
        // Adapter for the JPA provider - Hibernate
        factory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // Package with the User and Company entities
        factory.setPackagesToScan("pl.jlabs.example.domain");

        var jpaProperties = new Properties();
        jpaProperties.put("hibernate.hbm2ddl.auto", "validate");
        // Table naming strategy: ClassName -> class_name
        jpaProperties.put("hibernate.physical_naming_strategy", CamelCaseToUnderscoresNamingStrategy.class.getName());
        factory.setJpaProperties(jpaProperties);

        return factory;
    }

    @Bean
    JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        var transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }
}
