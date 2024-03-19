package pl.jlabs.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import pl.jlabs.example.displayer.DataDisplayer;
import pl.jlabs.example.provider.DataProvider;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class BeanConfig {
    @Bean
    public DataDisplayer holidayDataDisplayer() {
        return () -> System.out.println(christmasDataProvider().provideData());
    }

    @Bean
    public DataProvider christmasDataProvider() {
        System.out.println("Creating christmasDataProvider!");
        return () -> "Merry Christmas!";
    }
}
