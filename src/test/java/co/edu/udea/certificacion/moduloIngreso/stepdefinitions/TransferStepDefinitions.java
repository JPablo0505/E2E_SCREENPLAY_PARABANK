package co.edu.udea.certificacion.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.moduloIngreso.tasks.Transfer;
import co.edu.udea.certificacion.moduloIngreso.questions.TransferSuccess;

import io.cucumber.java.After;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class TransferStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor actor = Actor.named("Prueba");

    @When("he transfers an amount of {int} from the first account to the new account")
    public void transferFunds(int amount) {
        actor.can(BrowseTheWeb.with(herBrowser));
        actor.attemptsTo(Transfer.funds(amount));
    }

    @When("he transfers an amount of {int} to the same account")
    public void transferFundsToSameAccount(int amount) {
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

    @After
    public void closeSession() {
        if (herBrowser != null) {
            herBrowser.manage().deleteAllCookies();
        }
    }
}
