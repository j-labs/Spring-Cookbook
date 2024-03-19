package pl.jlabs.example.repositories;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.jlabs.example.domain.User;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByFirstName(String firstName);

    @Query("SELECT * FROM user_data u JOIN company c ON u.company_id = c.id WHERE c.company_name = :companyName")
    List<User> findByCompanyName(@Param("companyName") String companyName);

    @Modifying
    @Query("UPDATE user_data SET last_name = :newLastName WHERE last_name = :oldLastName")
    int updateLastNameByPreviousLastName(@Param("oldLastName")String oldLastName, @Param("newLastName")String newLastName);
}

