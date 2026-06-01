package co.edu.udea.certificacion.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class TransferError implements Question<String> {

    public static TransferError message() {
        return new TransferError();
    }

    @Override
    public String answeredBy(Actor actor) {
        By errorLocator = By.xpath("//div[@id='showError']/p[@class='error']");

        actor.attemptsTo(
            WaitUntil.the(errorLocator, WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds()
        );

        String rawError = Text.of(errorLocator).answeredBy(actor);
        return rawError != null ? rawError.trim() : "";
    }
}
