package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.AccountSummaryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class LoginSuccess implements Question<Boolean> {

    public static LoginSuccess isDashboardVisible() {
        return new LoginSuccess();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        actor.attemptsTo(
            WaitUntil.the(By.xpath(AccountSummaryPage.TABLE_ACCOUNTS), WebElementStateMatchers.isVisible())
                .forNoMoreThan(7).seconds()
        );
        
        return Visibility.of(By.xpath(AccountSummaryPage.TABLE_ACCOUNTS)).answeredBy(actor);
    }
}