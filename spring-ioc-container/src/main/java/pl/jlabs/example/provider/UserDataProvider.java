package pl.jlabs.example.provider;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class UserDataProvider implements DataProvider {
    @Override
    public String provideData() {
        return "Jan Kowalski";
    }
}
