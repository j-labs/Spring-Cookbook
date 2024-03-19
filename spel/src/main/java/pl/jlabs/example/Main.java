package pl.jlabs.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import pl.jlabs.example.domain.Company;
import pl.jlabs.example.domain.User;
import pl.jlabs.example.providers.DataProvider;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        literals();
        fields();
        collections();
        comparison();
        logic();
        setters();
        beans();
        ternaryAndElvis();
        safeNavigation();
    }

    private static void literals() {
        var parser = new SpelExpressionParser();
        var exp = parser.parseExpression("12.3");
        var number = exp.getValue(Double.class);
        System.out.println(number);

        exp = parser.parseExpression("true");
        var bool = exp.getValue(Boolean.class);
        System.out.println(bool);
    }

    private static void fields() {
        var parser = new SpelExpressionParser();

        var user = new User("Jan", "Nowak", new Company("J-labs"));
        var firstName = parser.parseExpression("firstName");
        var context = new StandardEvaluationContext(user);
        System.out.println(firstName.getValue(context));

        var companyName = parser.parseExpression("employer.companyName");
        System.out.println(companyName.getValue(context));
    }

    private static void collections() {
        var parser = new SpelExpressionParser();

        var primes = (List<Integer>) parser.parseExpression("{1, 2, 3, 5, 7, 11}").getValue();
        System.out.println(primes);

        var evenAndOdd = (List<List<Integer>>) parser.parseExpression("{{1,3,5,7,9,11},{2,4,6,8,10}}").getValue();
        System.out.println(evenAndOdd);

        var months = (Map<Integer, String>) parser.parseExpression("{1: 'January', 3: 'March'}").getValue();
        System.out.println(months);

        var reversedOrder = (int[]) parser.parseExpression("new int[]{9,8,7,6,5,4,3,2,1,0}").getValue();
        System.out.println(Arrays.toString(reversedOrder));
    }

    private static void comparison() {
        var parser = new SpelExpressionParser();

        var numbersEqual = parser.parseExpression("1 == 1.0").getValue(Boolean.class);
        System.out.println("1 == 1.0 : " + numbersEqual);

        var numberLower = parser.parseExpression("1 < 3.14").getValue(Boolean.class);
        System.out.println("1 < 3.14 : " + numberLower);

        var stringEqual = parser.parseExpression("'Ala' > 'Ola'").getValue(Boolean.class);
        System.out.println("'Ala' > 'Ola' : " + stringEqual);

        var lowNumberHigherThanNull = parser.parseExpression("null > -9999999").getValue(Boolean.class);
        System.out.println("null > -9999999 : " + lowNumberHigherThanNull);
    }

    private static void logic() {
        var parser = new SpelExpressionParser();

        var andTrue = parser.parseExpression("null < -9999999 and 'Ala' < 'Ola' ").getValue(Boolean.class);
        System.out.println("null < -9999999 and 'Ala' < 'Ola' : " + andTrue);

        var andFalse = parser.parseExpression("null < -9999999 && 'Ala' > 'Ola' ").getValue(Boolean.class);
        System.out.println("null < -9999999 && 'Ala' > 'Ola'  : " + andFalse);

        var orTrue = parser.parseExpression("null < -9999999 OR 'Ala' > 'Ola' ").getValue(Boolean.class);
        System.out.println("null < -9999999 OR 'Ala' > 'Ola'  : " + orTrue);

        var notTrue = parser.parseExpression("not true").getValue(Boolean.class);
        System.out.println("not true  : " + notTrue);
    }

    private static void setters() {
        var parser = new SpelExpressionParser();

        var user = new User("Jan", "Nowak", new Company("J-labs"));
        var firstName = parser.parseExpression("firstName");
        var context = new StandardEvaluationContext(user);
        System.out.println(firstName.getValue(context));

        firstName.setValue(context, "Adam");
        System.out.println(user.getFirstName());
    }

    private static void beans() {
        var context = new AnnotationConfigApplicationContext(BeanConfig.class);
        var parser = new SpelExpressionParser();

        var evaluationContext = new StandardEvaluationContext();
        evaluationContext.setBeanResolver(new BeanFactoryResolver(context.getAutowireCapableBeanFactory()));
        var bean = (DataProvider) parser.parseExpression("@userDataProvider").getValue(evaluationContext);
        System.out.println("Dane z beana userDataProvider: " + Optional.ofNullable(bean)
                .map(DataProvider::provideData)
                .orElse("")
        );
    }

    private static void ternaryAndElvis() {
        var parser = new SpelExpressionParser();

        var user = new User("Jan", "Nowak", new Company("J-labs"));

        var context = new StandardEvaluationContext(user);
        var ternaryOperatorResult = parser
                .parseExpression("firstName != null ? 'User name is ' + firstName : 'User is nameless'")
                .getValue(context, String.class);
        System.out.println(ternaryOperatorResult);

        var firstName = parser.parseExpression("firstName");
        firstName.setValue(context, null);
        var elvisOperatorResult = parser.parseExpression("'User name is ' + (firstName?:'Elvis')")
                .getValue(context, String.class);
        System.out.println(elvisOperatorResult);
    }

    private static void safeNavigation() {
        var parser = new SpelExpressionParser();

        var user = new User("Jan", "Nowak", null);

        var context = new StandardEvaluationContext(user);
        var companyName = parser.parseExpression("employer?.companyName");
        System.out.println(companyName.getValue(context));
    }
}
