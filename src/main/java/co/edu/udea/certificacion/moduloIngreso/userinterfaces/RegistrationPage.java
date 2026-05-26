package co.edu.udea.certificacion.moduloIngreso.userinterfaces;

public class RegistrationPage {
    public static final String LINK_REGISTER = "//a[contains(@href, 'register.htm')]";
    public static final String INPUT_FIRST_NAME = "//input[@id='customer.firstName']";
    public static final String INPUT_LAST_NAME = "//input[@id='customer.lastName']";
    public static final String INPUT_STREET = "//input[@id='customer.address.street']";
    public static final String INPUT_CITY = "//input[@id='customer.address.city']";
    public static final String INPUT_STATE = "//input[@id='customer.address.state']";
    public static final String INPUT_ZIP_CODE = "//input[@id='customer.address.zipCode']";
    public static final String INPUT_PHONE = "//input[@id='customer.phoneNumber']";
    public static final String INPUT_SSN = "//input[@id='customer.ssn']";
    
    public static final String INPUT_USERNAME = "//input[@id='customer.username']";
    public static final String INPUT_PASSWORD = "//input[@id='customer.password']";
    public static final String INPUT_CONFIRM_PASSWORD = "//input[@id='repeatedPassword']";
    
    public static final String BUTTON_REGISTER = "//input[@value='Register']";

    public static final String ERROR_FIRST_NAME = "//span[@id='customer.firstName.errors']";
    public static final String ERROR_LAST_NAME = "//span[@id='customer.lastName.errors']";
    public static final String ERROR_PASSWORD = "//span[@id='repeatedPassword.errors']";
}