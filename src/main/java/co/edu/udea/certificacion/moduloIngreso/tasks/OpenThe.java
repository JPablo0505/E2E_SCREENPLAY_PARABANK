package co.edu.udea.certificacion.moduloIngreso.tasks;

import co.edu.udea.certificacion.moduloIngreso.userinterfaces.ParabankHomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Open;

public class OpenThe implements Task {

    private final String targetPage;
    private ParabankHomePage parabankHomePage;

    public OpenThe(String targetPage) {
        this.targetPage = targetPage;
    }

    public static OpenThe bankHomePage() {
        return Tasks.instrumented(OpenThe.class, "homePage");
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if ("homePage".equals(targetPage)) {
            actor.attemptsTo(
                Open.browserOn(parabankHomePage)
            );
        }
    }
}