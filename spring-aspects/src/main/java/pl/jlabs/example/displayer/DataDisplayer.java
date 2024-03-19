package pl.jlabs.example.displayer;

import java.util.Optional;

public interface DataDisplayer {
    void displayData();

    default String changeDataToUpperCase(String data) {
        return Optional.ofNullable(data).map(String::toUpperCase).orElse(null);
    }
}
