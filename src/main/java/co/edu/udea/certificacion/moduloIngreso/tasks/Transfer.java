package co.edu.udea.certificacion.moduloIngreso.tasks;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.TransferFundsPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;

public class Transfer implements Task {

    private final String amount;
    private final boolean sameAccount;

    public Transfer(String amount) {
        this.amount = amount;
        this.sameAccount = false;
    }

    public Transfer(String amount, boolean sameAccount) {
        this.amount = amount;
        this.sameAccount = sameAccount;
    }

    public static Transfer funds(String amount) {
        return Tasks.instrumented(Transfer.class, amount);
    }

    public static Transfer fundsToSameAccount(String amount) {
        return Tasks.instrumented(Transfer.class, amount, true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int toIndex = sameAccount ? 0 : 1;

        actor.attemptsTo(
            WaitUntil.the(By.xpath(TransferFundsPage.LINK_TRANSFER_FUNDS), WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds(),
            Click.on(By.xpath(TransferFundsPage.LINK_TRANSFER_FUNDS)),

            WaitUntil.the(By.xpath(TransferFundsPage.INPUT_AMOUNT), WebElementStateMatchers.isClickable())
                .forNoMoreThan(7).seconds()
        );

        try { Thread.sleep(2500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }

        actor.attemptsTo(
            Enter.theValue(amount).into(By.xpath(TransferFundsPage.INPUT_AMOUNT)),

            WaitUntil.the(By.xpath(TransferFundsPage.SELECT_FROM_ACCOUNT), WebElementStateMatchers.isEnabled())
                .forNoMoreThan(5).seconds(),
            SelectFromOptions.byIndex(0).from(By.xpath(TransferFundsPage.SELECT_FROM_ACCOUNT)),
            
            WaitUntil.the(By.xpath(TransferFundsPage.SELECT_TO_ACCOUNT), WebElementStateMatchers.isEnabled())
                .forNoMoreThan(5).seconds(),
            SelectFromOptions.byIndex(toIndex).from(By.xpath(TransferFundsPage.SELECT_TO_ACCOUNT)),

            WaitUntil.the(By.xpath(TransferFundsPage.BUTTON_TRANSFER), WebElementStateMatchers.isClickable())
                .forNoMoreThan(5).seconds(),
            Click.on(By.xpath(TransferFundsPage.BUTTON_TRANSFER))
        );

        try { Thread.sleep(2000); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}