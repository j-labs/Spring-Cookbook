package pl.jlabs.example.displayer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.jlabs.example.provider.DataProvider;


@Component
public class UserDataDisplayer implements DataDisplayer {
    private final DataProvider provider;

    public UserDataDisplayer(@Qualifier("userDataProvider") DataProvider provider) {
        this.provider = provider;
    }

    @Override
    public void displayData() {
        System.out.println(provider.provideData());
    }
}
