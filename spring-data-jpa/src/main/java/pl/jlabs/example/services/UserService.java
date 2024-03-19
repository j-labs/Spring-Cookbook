package pl.jlabs.example.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.jlabs.example.domain.Company;
import pl.jlabs.example.domain.User;
import pl.jlabs.example.repositories.CompanyRepository;
import pl.jlabs.example.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public void modifyUsers() {
        companyRepository.findByCompanyName("J-Labs").ifPresent(jlabs -> {
            var user = new User("Jane", "Smith", jlabs);
            userRepository.save(user);
        });

        userRepository.findAll().forEach(user -> {
            if ("Sam".equals(user.getFirstName())) {
                user.setFirstName("Max");
                userRepository.save(user);
            } else if ("Anna".equals(user.getFirstName())) {
                userRepository.delete(user);
            }
        });
    }

    @Transactional(readOnly = true)
    public void printUsersByCompanyName(String companyName) {
        var users = companyRepository.findByCompanyName(companyName)
                .map(Company::getUsers)
                .orElse(null);
        System.out.println("Users working for " + companyName + ": " + users);
    }

    @Transactional
    public void resetCompaniesAndUsers() {
        userRepository.deleteAll();
        companyRepository.deleteAll();

        var jlabs = new Company("J-Labs");
        companyRepository.save(jlabs);

        userRepository.saveAll(List.of(
                new User("Anna", "Kowalska", jlabs),
                new User("Sam", "Johnson", jlabs)
        ));
    }
}
