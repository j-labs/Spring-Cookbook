package pl.jlabs.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.jlabs.example.domain.Company;
import pl.jlabs.example.domain.User;
import pl.jlabs.example.repositories.CompanyRepository;
import pl.jlabs.example.repositories.UserRepository;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(BeanConfig.class, JdbcConfig.class);
        var userRepository = context.getBean(UserRepository.class);
        var companyRepository = context.getBean(CompanyRepository.class);

        insertCompany(companyRepository);

        insertAndDeleteUsers(userRepository, companyRepository);

        insertAndUpdateUsers(userRepository, companyRepository);

        modifyingQuery(userRepository, companyRepository);
    }

    private static void insertCompany(CompanyRepository companyRepository) {
        companyRepository.deleteAll();
        companyRepository.save(new Company("J-Labs"));
    }

    private static void insertAndDeleteUsers(UserRepository userRepository, CompanyRepository companyRepository) {
        System.out.println("=== INSERT AND DELETE ===");
        userRepository.deleteAll();

        var jlabs = companyRepository.findByCompanyName("J-Labs");

        var user = new User("Adam", "Nowak", jlabs.getId());
        userRepository.save(user);
        var user2 = new User("Anna", "Kowalska", jlabs.getId());
        userRepository.save(user2);
        var user3 = new User("Anna", "Nowak", jlabs.getId());
        userRepository.save(user3);

        var annaUsers = userRepository.findByFirstName("Anna");
        var jlabsUsers = userRepository.findByCompanyName("J-Labs");

        System.out.println("Users before delete");

        System.out.println("Users named Anna: " + annaUsers);
        System.out.println("Users working in J-Labs: " + jlabsUsers);

        userRepository.delete(user3);

        System.out.println("Users after delete");

        var annaUsers2 = userRepository.findByFirstName("Anna");
        var jlabsUsers2 = userRepository.findByCompanyName("J-Labs");
        System.out.println("Users named Anna: " + annaUsers2);
        System.out.println("Users working in J-Labs: " + jlabsUsers2);
    }

    private static void insertAndUpdateUsers(UserRepository userRepository, CompanyRepository companyRepository) {
        System.out.println("=== INSERT AND UPDATE ===");
        userRepository.deleteAll();
        var jlabs = companyRepository.findByCompanyName("J-Labs");

        var user = new User("Adam", "Nowak", jlabs.getId());
        userRepository.save(user);

        var jLabsUsers = userRepository.findByCompanyName("J-Labs");
        System.out.println("Users working in J-Labs: " + jLabsUsers);

        user.setFirstName("Sam");
        userRepository.save(user);

        var jLabsUsers2 = userRepository.findByCompanyName("J-Labs");
        System.out.println("Users working in J-Labs: " + jLabsUsers2);
    }

    private static void modifyingQuery(UserRepository userRepository, CompanyRepository companyRepository) {
        System.out.println("=== MODIFYING QUERY ===");
        userRepository.deleteAll();
        var jlabs = companyRepository.findByCompanyName("J-Labs");

        var user = new User("Anna", "Nowak", jlabs.getId());
        userRepository.save(user);

        userRepository.updateLastNameByPreviousLastName("Nowak", "Smith");

        var jLabsUsers = userRepository.findByCompanyName("J-Labs");
        System.out.println("Users working in J-Labs: " + jLabsUsers);
    }
}
