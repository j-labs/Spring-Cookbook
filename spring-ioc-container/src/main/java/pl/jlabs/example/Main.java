package pl.jlabs.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.jlabs.example.displayer.DataDisplayer;

@ComponentScan
public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(BeanConfig.class);

        var dataDisplayer = context.getBean("dataDisplayer", DataDisplayer.class);
        dataDisplayer.displayData();

        var holidayDataDisplayer = context.getBean("holidayDataDisplayer", DataDisplayer.class);
        holidayDataDisplayer.displayData();
    }
}
