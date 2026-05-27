package co.edu.udea.certificacion.moduloIngreso.userinterfaces;

public class AccountSummaryPage {
    // Menú lateral izquierdo (UX)
    public static final String LINK_ACCOUNTS_OVERVIEW = "//a[contains(@href, 'overview.htm')]";
    public static final String LINK_OPEN_NEW_ACCOUNT = "//a[contains(@href, 'openaccount.htm')]";

    // Elementos de la tabla de Accounts Overview (Basado en ID de la tabla, no en texto duro)
    public static final String TABLE_ACCOUNTS = "//table[@id='accountTable']";
    public static final String LINK_FIRST_ACCOUNT_NUMBER = "//table[@id='accountTable']/tbody/tr[1]/td[1]/a";

    // Formulario de apertura de cuenta
    public static final String SELECT_ACCOUNT_TYPE = "//select[@id='type']";
    public static final String OPTION_SAVINGS = "//select[@id='type']/option[@value='1']";
    public static final String BUTTON_OPEN_ACCOUNT = "//input[@value='Open New Account']";

    // Elementos de la UX de éxito
    public static final String LINK_NEW_ACCOUNT_NUMBER = "//a[@id='newAccountId']";
}