package co.edu.udea.certificacion.moduloIngreso.stepdefinitions;

import co.edu.udea.certificacion.moduloIngreso.tasks.OpenThe;
import co.edu.udea.certificacion.moduloIngreso.tasks.SignUp;
import co.edu.udea.certificacion.moduloIngreso.questions.RegistrationError;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class BankOperationsStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor actor = Actor.named("Juan");

    @Given("that the actor opens the Parabank home page")
    public void openParabankHomePage() {
        actor.can(BrowseTheWeb.with(herBrowser));
        actor.wasAbleTo(OpenThe.bankHomePage());
    }

    @When("he registers a new user with valid information")
    public void heRegistersANewUserWithValidInformation() {
        actor.attemptsTo(SignUp.withValidData());
    }

    @When("he opens a new savings account")
    public void heOpensANewSavingsAccount() {
        // Futura Task
    }

    @When("he transfers money into the new savings account")
    public void heTransfersMoneyIntoTheNewSavingsAccount() {
        // Futura Task
    }

    @Then("he should see the updated balance in the accounts overview screen")
    public void heShouldSeeTheUpdatedBalanceInTheAccountsOverviewScreen() {
        // Futura Question
    }

    @When("he attempts to register with {string}, {string}, {string}, and {string}")
    public void heAttemptsToRegisterWithAnd(String firstName, String lastName, String password, String confirmPassword) {
        actor.attemptsTo(
            SignUp.withData(firstName, lastName, password, confirmPassword)
        );
    }

    @Then("he should see the registration error in the {string} field as {string}")
    public void heShouldSeeTheRegistrationErrorInTheFieldAs(String field, String expectedErrorMessage) {
        actor.should(
            seeThat("El mensaje de error visible en la interfaz", 
                RegistrationError.displayedFor(field.trim()), equalTo(expectedErrorMessage))
        );
    }

    @After
    public void closeSession() {
        if (herBrowser != null) {
            herBrowser.manage().deleteAllCookies();
        }
    }
}