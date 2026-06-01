package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.AccountSummaryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class BalanceUpdated implements Question<Boolean> {

    public static BalanceUpdated isVisible() {
        return new BalanceUpdated();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        By firstBalance = By.xpath(AccountSummaryPage.FIRST_ACCOUNT_BALANCE);

        actor.attemptsTo(
            WaitUntil.the(firstBalance, WebElementStateMatchers.isVisible())
                .forNoMoreThan(7).seconds()
        );

        String balance = Text.of(firstBalance).answeredBy(actor);
        return balance != null && !balance.trim().isEmpty();
    }
}
