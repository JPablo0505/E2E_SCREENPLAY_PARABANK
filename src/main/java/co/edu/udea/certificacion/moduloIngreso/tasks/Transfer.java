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

    private final int amount;
    private final boolean sameAccount;

    public Transfer(int amount) {
        this.amount = amount;
        this.sameAccount = false;
    }

    public Transfer(int amount, boolean sameAccount) {
        this.amount = amount;
        this.sameAccount = sameAccount;
    }

    public static Transfer funds(int amount) {
        return Tasks.instrumented(Transfer.class, amount);
    }

    public static Transfer fundsToSameAccount(int amount) {
        return Tasks.instrumented(Transfer.class, amount, true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        int toIndex = sameAccount ? 0 : 1;

        actor.attemptsTo(
            WaitUntil.the(By.xpath(TransferFundsPage.LINK_TRANSFER_FUNDS), WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds(),
            Click.on(By.xpath(TransferFundsPage.LINK_TRANSFER_FUNDS)),

            WaitUntil.the(By.xpath(TransferFundsPage.INPUT_AMOUNT), WebElementStateMatchers.isVisible())
                .forNoMoreThan(5).seconds(),

            Enter.theValue(String.valueOf(amount)).into(By.xpath(TransferFundsPage.INPUT_AMOUNT)),

            SelectFromOptions.byIndex(0).from(By.xpath(TransferFundsPage.SELECT_FROM_ACCOUNT)),
            SelectFromOptions.byIndex(toIndex).from(By.xpath(TransferFundsPage.SELECT_TO_ACCOUNT)),

            Click.on(By.xpath(TransferFundsPage.BUTTON_TRANSFER))
        );
    }
}
