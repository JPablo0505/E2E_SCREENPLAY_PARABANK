package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.RegistrationPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
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
        if ("firstName".equals(field)) {
            return Text.of(By.xpath(RegistrationPage.ERROR_FIRST_NAME)).answeredBy(actor);
        } else if ("lastName".equals(field)) {
            return Text.of(By.xpath(RegistrationPage.ERROR_LAST_NAME)).answeredBy(actor);
        } else if ("password".equals(field)) {
            return Text.of(By.xpath(RegistrationPage.ERROR_PASSWORD)).answeredBy(actor);
        }
        return "";
    }
}