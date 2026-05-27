package co.edu.udea.certificacion.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class RegistrationSuccess implements Question<String> {

    private static final By WELCOME_TITLE = By.xpath("//h1[@class='title']");

    public static RegistrationSuccess message() {
        return new RegistrationSuccess();
    }

    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(WELCOME_TITLE, WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds()
        );
        
        String fullText = Text.of(WELCOME_TITLE).answeredBy(actor);
        
        if (fullText != null && fullText.contains("Welcome")) {
            return "Welcome";
        }
        
        return fullText;
    }
}