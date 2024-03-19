package pl.jlabs.example.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.jlabs.example.domain.Company;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Integer> {
    Company findByCompanyName(String name);
}
