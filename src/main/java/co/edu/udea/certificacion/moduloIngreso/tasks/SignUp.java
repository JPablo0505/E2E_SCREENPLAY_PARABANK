package co.edu.udea.certificacion.moduloIngreso.tasks;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.RegistrationPage;
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
    
    private final String uniqueUser = "pablo_" + System.currentTimeMillis();

    public SignUp(String firstName, String lastName, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public static SignUp withValidData() {
        return Tasks.instrumented(SignUp.class, "Juan Pablo", "Valencia", "Udea2026*", "Udea2026*");
    }

    public static SignUp withData(String firstName, String lastName, String password, String confirmPassword) {
        return Tasks.instrumented(SignUp.class, firstName, lastName, password, confirmPassword);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
            Click.on(By.xpath(RegistrationPage.LINK_REGISTER)),
            Enter.theValue(this.firstName).into(By.xpath(RegistrationPage.INPUT_FIRST_NAME)),
            Enter.theValue(this.lastName).into(By.xpath(RegistrationPage.INPUT_LAST_NAME)),
            
            Enter.theValue("Calle 67 # 53-108").into(By.xpath(RegistrationPage.INPUT_STREET)),
            Enter.theValue("Medellín").into(By.xpath(RegistrationPage.INPUT_CITY)),
            Enter.theValue("Antioquia").into(By.xpath(RegistrationPage.INPUT_STATE)),
            Enter.theValue("050010").into(By.xpath(RegistrationPage.INPUT_ZIP_CODE)),
            Enter.theValue("3001234567").into(By.xpath(RegistrationPage.INPUT_PHONE)),
            Enter.theValue("123-456-789").into(By.xpath(RegistrationPage.INPUT_SSN)),
            
            Enter.theValue(uniqueUser).into(By.xpath(RegistrationPage.INPUT_USERNAME)),
            
            Enter.theValue(this.password).into(By.xpath(RegistrationPage.INPUT_PASSWORD)),
            Enter.theValue(this.confirmPassword).into(By.xpath(RegistrationPage.INPUT_CONFIRM_PASSWORD)),
            
            Click.on(By.xpath(RegistrationPage.BUTTON_REGISTER))
        );
    }
}