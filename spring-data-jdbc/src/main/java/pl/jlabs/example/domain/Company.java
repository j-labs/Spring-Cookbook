package pl.jlabs.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Company {
    @Id private Integer id;
    @Setter @NonNull private String companyName;
}
