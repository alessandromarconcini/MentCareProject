package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePatientPO extends PageObject {

    @FindBy(xpath = "//h1")
    private WebElement titleHomePatient;

    @FindBy(xpath = "//input[1]")
    private WebElement namePatient;

    @FindBy(xpath = "//input[2]")
    private WebElement surnamePatient;

    @FindBy(xpath = "//input[3]")
    private WebElement passwordPatient;

    @FindBy(xpath = "//input[4]")
    private WebElement birthPatient;

    @FindBy(xpath = "//input[5]")
    private WebElement birthPlacePatient;

    @FindBy(xpath = "//input[6]")
    private WebElement fiscalCodePatient;

    @FindBy(xpath = "//input[7]")
    private WebElement phoneNumberPatient;

    @FindBy(xpath = "//input[8]")
    private WebElement pathologyPatient;

    @FindBy(xpath = "//input[@id='dangerous'][@value='Sì']") // 9
    private WebElement dangerousPatient;

    @FindBy(xpath = "//input[@id='Notdangerous'][@value='No']") // 10
    private WebElement notDangerousPatient;

    @FindBy(xpath = "//input[12]")
    private WebElement BottomRegistration;

    @FindBy(xpath = "//input[13]")
    private WebElement BottomBackward;

    @FindBy(xpath = "//form[@action='/createPatient']//button[@id='createPatient']")
    private WebElement createPatient;

    public CreatePatientPO(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomePatient() { return this.titleHomePatient.getText();}

    public void enterNamePatient(String namePatient) {
        this.namePatient.clear();
        this.namePatient.sendKeys(namePatient);
    }
    public void enterSurnamePatient(String surnamePatient) {
        this.surnamePatient.clear();
        this.surnamePatient.sendKeys(surnamePatient);
    }
    public void enterPasswordPatient(String passwordPatient) {
        this.passwordPatient.clear();
        this.passwordPatient.sendKeys(passwordPatient);
    }
    public void enterBirthPatient(String birthPatient) {
        this.birthPatient.clear();
        this.birthPatient.sendKeys(birthPatient);
    }
    public void enterFiscalCodePatient(String fiscalCodePatient) {
        this.fiscalCodePatient.clear();
        this.fiscalCodePatient.sendKeys(fiscalCodePatient);
    }

    public void enterPhoneNumberPatient(String phoneNumber) {
        this.phoneNumberPatient.clear();
        this.phoneNumberPatient.sendKeys(phoneNumber);
    }

    public void enterPathologyPatient(String pathologyPatient) {
        this.pathologyPatient.clear();
        this.pathologyPatient.sendKeys(pathologyPatient);
    }

    public void enterBirthPlacePatient(String birthPlacePatient) {
        this.birthPlacePatient.clear();
        this.birthPlacePatient.sendKeys(birthPlacePatient);
    }
    public ManagerPageObject BottomRegistration() {
        this.BottomRegistration.click();
        return new ManagerPageObject(driver);
    }

    public ManagerPageObject radioButtonDangerous() {
        this.dangerousPatient.click();
        return new ManagerPageObject(driver);
    }

    public ManagerPageObject BottomBackward() {
        this.BottomBackward.click();
        return new ManagerPageObject(driver);
    }

    public ManagerPageObject createPatient() {
        this.createPatient.click();
        return new ManagerPageObject(driver);
    }
}
