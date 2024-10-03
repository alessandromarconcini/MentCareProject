package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateDoctorPO extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleCreationDoctor;

    @FindBy(xpath = "//input[1]")
    private WebElement nameDoctor;

    @FindBy(xpath = "//input[2]")
    private WebElement surnameDoctor;

    @FindBy(xpath = "//input[3]")
    private WebElement passwordDoctor;

    @FindBy(xpath = "//input[4]")
    private WebElement birthDoctor;

    @FindBy(xpath = "//input[5]")
    private WebElement birthPlaceDoctor;

    @FindBy(xpath = "//input[6]")
    private WebElement fiscalCodeDoctor;

    @FindBy(xpath = "//input[7]")
    private WebElement specializationDoctor;

    @FindBy(xpath = "//input[8]")
    private WebElement phoneNumberDoctor;

    @FindBy(xpath = "//input[10]")
    private WebElement BottomRegistration;

    @FindBy(xpath = "//input[11]")
    private WebElement BottomBackward;

    @FindBy(xpath = "//form[@action='/createDoctor']//button[@id='NewDoctor']") // è in ProfilePageDoctor
    private WebElement createDoctor;

    public CreateDoctorPO(WebDriver driver) { super(driver);}

    public String getTitleCreationDoctor() { return this.titleCreationDoctor.getText();}

    // Metodi per inserire i dati
    public void enterNameDoctor(String nameDoctor) {
        this.nameDoctor.clear();
        this.nameDoctor.sendKeys(nameDoctor);
    }
    public void enterSurnameDoctor(String surnameDoctor) {
        this.surnameDoctor.clear();
        this.surnameDoctor.sendKeys(surnameDoctor);
    }
    public void enterPasswordDoctor(String passwordDoctor) {
        this.passwordDoctor.clear();
        this.passwordDoctor.sendKeys(passwordDoctor);
    }
    public void enterBirthDoctor(String birthDoctor) {
        this.birthDoctor.clear();
        this.birthDoctor.sendKeys(birthDoctor);
    }
    public void enterBirthPlaceDoctor(String birthPlaceDoctor) {
        this.birthPlaceDoctor.clear();
        this.birthPlaceDoctor.sendKeys(birthPlaceDoctor);
    }
    public void enterFiscalCodeDoctor(String fiscalCodeDoctor) {
        this.fiscalCodeDoctor.clear();
        this.fiscalCodeDoctor.sendKeys(fiscalCodeDoctor);
    }
    public void enterSpecializationDoctor(String specializationDoctor) {
        this.specializationDoctor.clear();
        this.specializationDoctor.sendKeys(specializationDoctor);
    }
    public void enterPhoneNumberDoctor(String phoneNumberDoctor) {
        this.phoneNumberDoctor.clear();
        this.phoneNumberDoctor.sendKeys(phoneNumberDoctor);
    }
    public ManagerPageObject BottomRegistration() {
        this.BottomRegistration.click();
        return new ManagerPageObject(driver);
    }
    public ManagerPageObject BottomBackward() {
        this.BottomBackward.click();
        return new ManagerPageObject(driver);
    }

    public ManagerPageObject createDoctor() {
        this.createDoctor.click();
        return new ManagerPageObject(driver);
    }
}
