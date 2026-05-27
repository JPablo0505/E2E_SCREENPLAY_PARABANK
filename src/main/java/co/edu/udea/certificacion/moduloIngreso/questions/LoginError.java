package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class LoginError implements Question<String> {

    public static LoginError message() {
        return new LoginError();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(By.xpath(LoginPage.ERROR_MESSAGE), WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds()
        );

        String rawError = Text.of(By.xpath(LoginPage.ERROR_MESSAGE)).answeredBy(actor);
        return rawError != null ? rawError.trim() : "";
    }
}