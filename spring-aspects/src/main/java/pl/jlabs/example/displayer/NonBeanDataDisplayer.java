package pl.jlabs.example.displayer;

public class NonBeanDataDisplayer implements DataDisplayer {
    @Override
    public void displayData() {
        System.out.println("I'm not a Spring bean so I don't trigger aspect invocation!");
    }
}
