package pl.jlabs.example.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class NotFoundException extends RuntimeException {
    @Getter
    private final Integer id;

    public NotFoundException(String message, Integer id) {
        super(message);
        this.id = id;
    }
}
