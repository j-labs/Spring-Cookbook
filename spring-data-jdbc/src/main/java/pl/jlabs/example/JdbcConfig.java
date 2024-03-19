package pl.jlabs.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

@EnableJdbcRepositories(basePackages = { "pl.jlabs.example.repositories" })
@PropertySource("classpath:application.properties")
public class JdbcConfig extends AbstractJdbcConfiguration {
    @Value("${database.url}") private String url;
    @Value("${database.username}") private String username;
    @Value("${database.password}") private String password;
    @Value("${database.schema}") private String schema;

    @Bean
    DataSource dataSource() {
        var driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        // Parametry połączenia
        driver.setUrl(url);
        driver.setUsername(username);
        driver.setPassword(password);
        driver.setSchema(schema);
        return driver;
    }

    @Bean
    NamedParameterJdbcOperations namedParameterJdbcOperations(DataSource dataSource) {
        // Klasa odpowiadająca za przekazywanie operacji SQL do bazy danych
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    TransactionManager transactionManager(DataSource dataSource) {
        // Klasa odpowiadająca za zarządzanie transakcjami
        return new DataSourceTransactionManager(dataSource);
    }
}
