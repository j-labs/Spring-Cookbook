package pl.jlabs.example.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "UserData")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Setter
    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @NonNull
    private Company company;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
