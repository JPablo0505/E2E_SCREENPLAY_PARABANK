package co.edu.udea.certificacion.moduloIngreso.tasks;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.AccountSummaryPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class OpenThe implements Task {

    private final String mode;

    public OpenThe(String mode) {
        this.mode = mode;
    }

    public static OpenThe bankHomePage() {
        return Tasks.instrumented(OpenThe.class, "HOME");
    }

    public static OpenThe accountsOverview() {
        return Tasks.instrumented(OpenThe.class, "OVERVIEW");
    }

    public static OpenThe newSavingsAccount() {
        return Tasks.instrumented(OpenThe.class, "NEW_ACCOUNT");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if ("HOME".equals(mode)) {
            actor.attemptsTo(Open.url("https://parabank.parasoft.com/parabank/index.htm"));
        } 
        else if ("OVERVIEW".equals(mode)) {
            actor.attemptsTo(
                WaitUntil.the(By.xpath(AccountSummaryPage.LINK_ACCOUNTS_OVERVIEW), WebElementStateMatchers.isVisible())
                    .forNoMoreThan(5).seconds(),
                Click.on(By.xpath(AccountSummaryPage.LINK_ACCOUNTS_OVERVIEW)),
                
                // Espera flexible a la estructura de la tabla, evitando congelamientos por AJAX
                WaitUntil.the(By.xpath(AccountSummaryPage.TABLE_ACCOUNTS), WebElementStateMatchers.isVisible())
                    .forNoMoreThan(7).seconds()
            );
            
            String checkingId = Text.of(By.xpath(AccountSummaryPage.LINK_FIRST_ACCOUNT_NUMBER)).answeredBy(actor);
            actor.remember("checkingAccountNumber", checkingId);
            
            // Delays nativos para la revisión del profesor
            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        } 
        else if ("NEW_ACCOUNT".equals(mode)) {
            actor.attemptsTo(
                WaitUntil.the(By.xpath(AccountSummaryPage.LINK_OPEN_NEW_ACCOUNT), WebElementStateMatchers.isVisible())
                    .forNoMoreThan(5).seconds(),
                Click.on(By.xpath(AccountSummaryPage.LINK_OPEN_NEW_ACCOUNT)),
                
                WaitUntil.the(By.xpath(AccountSummaryPage.SELECT_ACCOUNT_TYPE), WebElementStateMatchers.isEnabled())
                    .forNoMoreThan(5).seconds(),
                Click.on(By.xpath(AccountSummaryPage.SELECT_ACCOUNT_TYPE)),
                Click.on(By.xpath(AccountSummaryPage.OPTION_SAVINGS))
            );

            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

            actor.attemptsTo(Click.on(By.xpath(AccountSummaryPage.BUTTON_OPEN_ACCOUNT)));

            try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        }
    }
}