package pl.jlabs.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private Company employer;
}

