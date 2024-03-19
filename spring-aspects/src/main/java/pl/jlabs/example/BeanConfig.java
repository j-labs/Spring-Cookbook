package pl.jlabs.example;

import org.springframework.context.annotation.*;
import pl.jlabs.example.displayer.DataDisplayer;
import pl.jlabs.example.provider.DataProvider;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
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
