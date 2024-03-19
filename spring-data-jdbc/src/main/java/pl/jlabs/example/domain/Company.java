package pl.jlabs.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id private Integer id;
    @NonNull private String companyName;
}
