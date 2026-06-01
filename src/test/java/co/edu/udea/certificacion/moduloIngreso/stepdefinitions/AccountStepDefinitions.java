package co.edu.udea.certificacion.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.moduloIngreso.tasks.OpenThe;
import co.edu.udea.certificacion.moduloIngreso.questions.NewAccount;
import co.edu.udea.certificacion.moduloIngreso.questions.NewAccountVerified;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class AccountStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor actor = Actor.named("Prueba");

    @When("he verifies his initial checking account in the accounts overview")
    public void heVerifiesHisInitialCheckingAccountInTheAccountsOverview() {
        actor.can(BrowseTheWeb.with(herBrowser));
        actor.attemptsTo(OpenThe.accountsOverview());
    }

    @When("he opens a new {string} account using the initial checking as source")
    public void heOpensANewAccountUsingTheInitialCheckingAsSource(String accountType) {
        actor.attemptsTo(OpenThe.newAccountOfType(accountType));
    }

    @Then("he should see the successful account creation message")
    public void heShouldSeeTheSuccessfulAccountCreationMessage() {
        actor.should(
            seeThat("El mensaje de éxito de apertura de cuenta", 
                NewAccount.successMessage(), equalTo("Account Opened!"))
        );
    }

    @Then("he should see the new account listed in the accounts overview")
    public void heShouldSeeNewAccountInOverview() {
        actor.should(
            seeThat("La nueva cuenta aparece en el resumen de cuentas",
                NewAccountVerified.inOverview(), is(true))
        );
    }
}