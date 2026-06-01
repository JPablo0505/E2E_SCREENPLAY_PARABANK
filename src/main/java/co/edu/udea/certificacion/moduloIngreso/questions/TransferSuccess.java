package co.edu.udea.certificacion.moduloIngreso.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class TransferSuccess implements Question<String> {

    public static TransferSuccess message() {
        return new TransferSuccess();
    }

    @Override
    public String answeredBy(Actor actor) {
        By successTitle = By.xpath("//div[@id='showResult']/h1[@class='title']");

        actor.attemptsTo(
            WaitUntil.the(successTitle, WebElementStateMatchers.isVisible())
                .forNoMoreThan(7).seconds()
        );

        String fullText = Text.of(successTitle).answeredBy(actor);

        if (fullText != null && fullText.contains("Transfer Complete")) {
            return "Transfer Complete!";
        }
        return fullText != null ? fullText.trim() : "";
    }
}
