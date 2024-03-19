package pl.jlabs.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "user_data")
@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id private Integer id;
    @Setter @NonNull private String firstName;
    @NonNull private String lastName;
    @NonNull private Integer companyId;

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
