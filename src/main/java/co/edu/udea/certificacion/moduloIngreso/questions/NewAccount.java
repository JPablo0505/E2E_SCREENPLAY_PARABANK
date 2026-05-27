package co.edu.udea.certificacion.moduloIngreso.questions;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.AccountSummaryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class NewAccount implements Question<String> {

    public static NewAccount successMessage() {
        return new NewAccount();
    }

    @Override
    public String answeredBy(Actor actor) {
        // Le damos un margen de espera explícita para que el ID cargue en el DOM tras el clic
        actor.attemptsTo(
            WaitUntil.the(By.xpath(AccountSummaryPage.LINK_NEW_ACCOUNT_NUMBER), WebElementStateMatchers.isVisible())
                .forNoMoreThan(7).seconds()
        );
        
        String accNum = Text.of(By.xpath(AccountSummaryPage.LINK_NEW_ACCOUNT_NUMBER)).answeredBy(actor);
        actor.remember("newAccountNumber", accNum);
        
        if (accNum != null && !accNum.trim().isEmpty()) {
            return "Account Opened!";
        }
        return "";
    }
}