package co.edu.udea.certificacion.moduloIngreso.stepdefinitions;
import co.edu.udea.certificacion.moduloIngreso.tasks.SignUp;
import co.edu.udea.certificacion.moduloIngreso.questions.LoginSuccess;
import co.edu.udea.certificacion.moduloIngreso.questions.LoginError;

import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.annotations.Managed;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginStepDefinitions {

    @Managed(driver = "chrome")
    private WebDriver herBrowser;

    private Actor actor = Actor.named("StandardCustomer");

    @When("he logs in with username {string} and password {string}")
    public void heLogsInWithUsernameAndPassword(String username, String password) {
        actor.can(BrowseTheWeb.with(herBrowser));
        // Reutilizamos SignUp llamando a su nuevo método de Login
        actor.attemptsTo(SignUp.toLoginWith(username, password));
    }

    @Then("he should see his accounts overview page")
    public void heShouldSeeHisAccountsOverviewPage() {
        actor.should(
            seeThat("La visibilidad del panel de cuentas del cliente", 
                LoginSuccess.isDashboardVisible(), equalTo(true))
        );
    }

    @Then("he should see the login error message as {string}")
    public void heShouldSeeTheLoginErrorMessageAs(String expectedErrorMessage) {
        actor.should(
            seeThat("El mensaje de error visible en la interfaz de login", 
                LoginError.message(), equalTo(expectedErrorMessage.trim()))
        );
    }
}