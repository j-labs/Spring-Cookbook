package pl.jlabs.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jlabs.example.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByFirstName(String firstName);

    @Query(value = "SELECT u.* FROM user_data u JOIN company c ON u.company_id = c.id WHERE c.company_name = :companyName", nativeQuery = true)
    List<User> findByCompanyName(@Param("companyName") String companyName);

    @Modifying
    @Query(value = "UPDATE user_data SET last_name = :newLastName WHERE last_name = :oldLastName", nativeQuery = true)
    int updateLastNameByPreviousLastName(@Param("oldLastName") String oldLastName, @Param("newLastName") String newLastName);
}

