package pl.jlabs.example.services;

import lombok.RequiredArgsConstructor;
import pl.jlabs.example.domain.Company;
import pl.jlabs.example.domain.User;
import pl.jlabs.example.dto.CompanyDto;
import pl.jlabs.example.dto.UserDto;
import pl.jlabs.example.exceptions.NotFoundException;
import pl.jlabs.example.repositories.CompanyRepository;
import pl.jlabs.example.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAllWithCompany().stream()
                .map(user -> toUserDto(user, user.getCompany()))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<UserDto> getAllUsersByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName).map(company ->
                company.getUsers().stream().map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName(),
                        new CompanyDto(
                                company.getId(),
                                company.getCompanyName(),
                                company.getCreatedBy()
                        ),
                        user.getCreatedBy()
                    )
                ).toList()
        ).orElse(Collections.emptyList());
    }

    @Transactional
    public UserDto createUser(UserDto userDto) {
        var company = updateOrCreateCompany(userDto.company());
        var user = new User(
                userDto.firstName(),
                userDto.lastName(),
                company
        );
        var savedUser = userRepository.save(user);
        return toUserDto(savedUser, company);
    }

    @Transactional
    public UserDto updateUser(int userId, UserDto userDto) {
        var user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found", userId)
        );
        user.setFirstName(userDto.firstName());
        user.setLastName(userDto.lastName());
        var company = updateOrCreateCompany(userDto.company());
        user.setCompany(company);
        var savedUser = userRepository.save(user);
        return toUserDto(savedUser, company);
    }

    private Company updateOrCreateCompany(CompanyDto companyDto) {
        if (companyDto.id() != null) {
            var company = companyRepository.findById(companyDto.id()).orElseThrow(
                    () -> new NotFoundException("Company not found", companyDto.id())
            );
            if (companyDto.companyName() != null) {
                company.setCompanyName(companyDto.companyName());
            }
            return company;
        }

        var company = new Company(companyDto.companyName());
        return companyRepository.save(company);
    }

    private static UserDto toUserDto(User user, Company company) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                new CompanyDto(company.getId(), company.getCompanyName(), company.getCreatedBy()),
                user.getCreatedBy()
        );
    }

}
