package co.edu.udea.certificacion.moduloIngreso.tasks;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.RegistrationPage;
import co.edu.udea.certificacion.moduloIngreso.userinterfaces.LoginPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.By;

public class SignUp implements Task {

    private final String firstName;
    private final String lastName;
    private final String password;
    private final String confirmPassword;
    private String usernameForLogin = "";
    private boolean isLoginAction = false;
    
    private final String uniqueUser = "user_" + System.currentTimeMillis();

    public SignUp(String firstName, String lastName, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public SignUp(String username, String password, boolean isLoginAction) {
        this.firstName = "";
        this.lastName = "";
        this.usernameForLogin = username;
        this.password = password;
        this.confirmPassword = "";
        this.isLoginAction = isLoginAction;
    }

    public static SignUp withValidData() {
        return Tasks.instrumented(SignUp.class, "TestFirstName", "TestLastName", "Udea2026*", "Udea2026*");
    }

    public static SignUp withData(String firstName, String lastName, String password, String confirmPassword) {
        return Tasks.instrumented(SignUp.class, firstName, lastName, password, confirmPassword);
    }

    public static SignUp toLoginWith(String username, String password) {
        return Tasks.instrumented(SignUp.class, username, password, true);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (isLoginAction) {
            actor.attemptsTo(
                Enter.theValue(this.usernameForLogin).into(By.xpath(LoginPage.INPUT_USERNAME)),
                Enter.theValue(this.password).into(By.xpath(LoginPage.INPUT_PASSWORD)),
                Click.on(By.xpath(LoginPage.BUTTON_LOGIN))
            );
        } else {
            actor.attemptsTo(
                Click.on(By.xpath(RegistrationPage.LINK_REGISTER)),
                Enter.theValue(this.firstName).into(By.xpath(RegistrationPage.INPUT_FIRST_NAME)),
                Enter.theValue(this.lastName).into(By.xpath(RegistrationPage.INPUT_LAST_NAME)),
                
                Enter.theValue("Calle Falsa 123").into(By.xpath(RegistrationPage.INPUT_STREET)),
                Enter.theValue("Medellin").into(By.xpath(RegistrationPage.INPUT_CITY)),
                Enter.theValue("Antioquia").into(By.xpath(RegistrationPage.INPUT_STATE)),
                Enter.theValue("050010").into(By.xpath(RegistrationPage.INPUT_ZIP_CODE)),
                Enter.theValue("55512345").into(By.xpath(RegistrationPage.INPUT_PHONE)),
                Enter.theValue("000-00-0000").into(By.xpath(RegistrationPage.INPUT_SSN)),
                
                Enter.theValue(uniqueUser).into(By.xpath(RegistrationPage.INPUT_USERNAME)),
                
                Enter.theValue(this.password).into(By.xpath(RegistrationPage.INPUT_PASSWORD)),
                Enter.theValue(this.confirmPassword).into(By.xpath(RegistrationPage.INPUT_CONFIRM_PASSWORD)),
                
                Click.on(By.xpath(RegistrationPage.BUTTON_REGISTER))
            );
        }
    }
}