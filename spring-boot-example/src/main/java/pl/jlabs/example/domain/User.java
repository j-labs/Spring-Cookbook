package pl.jlabs.example.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity(name = "UserData")
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @Setter
    private String firstName;

    @NonNull
    @Setter
    private String lastName;

    @NonNull
    @Setter
    @ManyToOne
    @JoinColumn(name="company_id")
    private Company company;

    @CreatedBy
    private String createdBy;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
