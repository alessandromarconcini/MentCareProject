package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPageObject extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleLogin;

    @FindBy(name = "username")
    private WebElement username;

    @FindBy(name = "password")
    private WebElement password;

    @FindBy(xpath = "//input[3]")
    private WebElement submit; // "invia richiesta" per accedere

    @FindBy(xpath = "//a[1]")
    private WebElement logoutManager;

    @FindBy(xpath = "//a[1]")
    private WebElement logoutDoctor;

    @FindBy(xpath = "//a[1]")
    private WebElement logoutNurse;

    @FindBy(xpath = "//a[1]")
    private WebElement logoutReceptionist;

    @FindBy(xpath = "//a[1]")
    private WebElement logoutPatient;

    public LoginPageObject(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleLogin() { return this.titleLogin.getText();}

    public void enterUsername(String username) {
        this.username.clear();
        this.username.sendKeys(username);
    }
    public void enterPassword(String password) {
        this.password.clear();
        this.password.sendKeys(password);
    }
    public PageObject submit() {
        this.submit.click();
        return new PageObject(driver);
    }
    public ManagerPageObject logoutManager() {
        this.logoutManager.click();
        return new ManagerPageObject(driver);
    }
    public DoctorPageObject logoutDoctor() {
        this.logoutDoctor.click();
        return new DoctorPageObject(driver);
    }
    public NursePageObject logoutNurse() {
        this.logoutNurse.click();
        return new NursePageObject(driver);
    }
    public PatientPageObject logoutPatient() {
        this.logoutPatient.click();
        return new PatientPageObject(driver);
    }
    public ReceptionistPageObject logoutReceptionist() {
        this.logoutReceptionist.click();
        return new ReceptionistPageObject(driver);
    }

}
