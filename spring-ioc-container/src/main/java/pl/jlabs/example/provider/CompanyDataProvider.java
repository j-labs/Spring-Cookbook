package pl.jlabs.example.provider;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
@Qualifier("displayableProvider")
@Order(2)
@Conditional(CompanyDataProvider.IsEnabled.class)
public class CompanyDataProvider implements DataProvider {
    static class IsEnabled implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            var enabled = context.getEnvironment().getProperty("provider.company.enabled", Boolean.class);
            return Boolean.TRUE.equals(enabled);
        }
    }

    @Override
    public String provideData() {
        return "J-labs";
    }
}
