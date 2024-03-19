package pl.jlabs.example.providers;

import org.springframework.stereotype.Component;

@Component
public class UserDataProvider implements DataProvider {
    @Override
    public String provideData() {
        return "Jan Kowalski";
    }
}
