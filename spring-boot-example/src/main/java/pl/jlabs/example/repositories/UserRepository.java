package pl.jlabs.example.repositories;

import pl.jlabs.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT user FROM UserData user INNER JOIN FETCH user.company")
    List<User> findAllWithCompany();
}
