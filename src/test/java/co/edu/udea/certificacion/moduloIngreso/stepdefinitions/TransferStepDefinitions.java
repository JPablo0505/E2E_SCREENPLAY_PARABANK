package co.edu.udea.certificacion.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.moduloIngreso.tasks.Transfer;
import co.edu.udea.certificacion.moduloIngreso.tasks.OpenThe;
import co.edu.udea.certificacion.moduloIngreso.questions.TransferSuccess;
import co.edu.udea.certificacion.moduloIngreso.questions.TransferError;
import co.edu.udea.certificacion.moduloIngreso.questions.BalanceUpdated;

import io.cucumber.java.After;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class TransferStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor actor = Actor.named("Prueba");

    @When("he transfers an amount of {string} from the first account to the new account")
    public void heTransfersAnAmountFromFirstToNewAccount(String amount) {
        actor.can(BrowseTheWeb.with(herBrowser));
        actor.attemptsTo(Transfer.funds(amount));
    }

    @When("he transfers an amount of {string} to the same account")
    public void transferFundsToSameAccount(String amount) {
        actor.can(BrowseTheWeb.with(herBrowser));
        actor.attemptsTo(Transfer.fundsToSameAccount(amount));
    }

    @Then("he should see the transfer confirmation message")
    public void verifyTransferSuccess() {
        actor.should(
            seeThat("El mensaje de confirmacion de transferencia",
                TransferSuccess.message(), equalTo("Transfer Complete!"))
        );
    }

    @Then("he should see the updated balance in the accounts overview")
    public void verifyBalanceUpdated() {
        actor.attemptsTo(OpenThe.accountsOverview());
        actor.should(
            seeThat("El saldo actualizado es visible tras la transferencia",
                BalanceUpdated.isVisible(), is(true))
        );
    }

    @Then("he should see a validation error indicating only numeric values")
    public void verifyNumericError() {
        actor.should(
            seeThat("Mensaje de error por caracteres alfabéticos",
                TransferError.message(), 
                equalTo("An internal error has occurred and has been logged."))
        );
    }

    @Then("the system should reject the transaction with an invalid amount error")
    public void verifyNegativeMoneyError() {
        actor.should(
            seeThat("El sistema rechaza montos negativos",
                TransferSuccess.message(), equalTo("Invalid Amount")) 
        );
    }

    @After
    public void closeSession() {
        if (herBrowser != null) {
            herBrowser.manage().deleteAllCookies();
        }
    }
}