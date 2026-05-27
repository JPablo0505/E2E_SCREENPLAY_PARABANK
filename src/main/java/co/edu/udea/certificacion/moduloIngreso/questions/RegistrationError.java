package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class RegistrationError implements Question<String> {

    private final String field;

    public RegistrationError(String field) {
        this.field = field;
    }

    public static RegistrationError displayedFor(String field) {
        return new RegistrationError(field);
    }

    @Override
    public String answeredBy(Actor actor) {
        String xpathExpression = "";

        if ("firstName".equals(field)) {
            xpathExpression = RegistrationPage.ERROR_FIRST_NAME;
        } else if ("lastName".equals(field)) {
            xpathExpression = RegistrationPage.ERROR_LAST_NAME;
        } else if ("password".equals(field)) {
            xpathExpression = RegistrationPage.ERROR_PASSWORD;
        } else if ("confirmPassword".equals(field)) {
            xpathExpression = RegistrationPage.ERROR_CONFIRM_PASSWORD;
        }

        if (xpathExpression.isEmpty()) {
            return "";
        }

        By errorLocator = By.xpath(xpathExpression);

        actor.attemptsTo(
            WaitUntil.the(errorLocator, WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds()
        );

        String rawError = Text.of(errorLocator).answeredBy(actor);
        return rawError != null ? rawError.trim() : "";
    }
}