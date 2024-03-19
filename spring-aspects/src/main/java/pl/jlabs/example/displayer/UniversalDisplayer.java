package pl.jlabs.example.displayer;

import org.springframework.stereotype.Component;
import pl.jlabs.example.provider.DataProvider;

import java.util.List;

@Component("dataDisplayer")
public class UniversalDisplayer implements DataDisplayer {
    private final List<DataProvider> dataProviders;

    public UniversalDisplayer(List<DataProvider> dataProviders) {
        this.dataProviders = dataProviders;
    }

    @Override
    public void displayData() {
        dataProviders.stream()
                .map(DataProvider::provideData)
                .map(this::changeDataToUpperCase)
                .forEach(System.out::println);
    }
}
