package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.tasks.OpenThe;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class NewAccountVerified implements Question<Boolean> {

    public static NewAccountVerified inOverview() {
        return new NewAccountVerified();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String newAccountId = actor.recall("newAccountNumber");

        if (newAccountId == null || newAccountId.trim().isEmpty()) {
            return false;
        }

        actor.attemptsTo(OpenThe.accountsOverview());

        By newAccountLink = By.xpath("//table[@id='accountTable']//a[text()='" + newAccountId + "']");

        actor.attemptsTo(
            WaitUntil.the(newAccountLink, WebElementStateMatchers.isVisible())
                .forNoMoreThan(7).seconds()
        );

        return Visibility.of(newAccountLink).answeredBy(actor);
    }
}
