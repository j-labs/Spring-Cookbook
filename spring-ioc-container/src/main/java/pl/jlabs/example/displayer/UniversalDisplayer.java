package pl.jlabs.example.displayer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.jlabs.example.provider.DataProvider;

import java.util.List;

@Component("dataDisplayer")
public class UniversalDisplayer implements DataDisplayer {
    private final List<DataProvider> dataProviders;
    private final String prefix;

    public UniversalDisplayer(
            @Qualifier("displayableProvider") List<DataProvider> dataProviders,
            @Value("${displayer.universal.prefix}") String prefix
    ) {
        this.dataProviders = dataProviders;
        this.prefix = prefix;
    }

    @Override
    public void displayData() {
        dataProviders.forEach(provider ->
                System.out.println(prefix + provider.provideData().toUpperCase())
        );
    }
}
