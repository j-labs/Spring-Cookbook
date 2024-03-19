package pl.jlabs.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.jlabs.example.services.UserService;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(BeanConfig.class, JpaConfig.class);

        var service = context.getBean(UserService.class);
        service.resetCompaniesAndUsers();
        service.modifyUsers();
        service.printUsersByCompanyName("J-Labs");
    }
}
