package pl.jlabs.example.provider;

import org.springframework.stereotype.Component;

@Component
public class CompanyDataProvider implements DataProvider {
    @Override
    public String provideData() {
        return "J-labs";
    }
}
