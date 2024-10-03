package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateNursePO extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleHomeNurse;

    @FindBy(xpath = "//input[1]")  // si gurda il "name" dell'input per tutti
    private WebElement nameNurse;

    @FindBy(xpath = "//input[2]")
    private WebElement surnameNurse;

    @FindBy(xpath = "//input[3]")
    private WebElement passwordNurse;

    @FindBy(xpath = "//input[4]")
    private WebElement birthNurse;

    @FindBy(xpath = "//input[5]")
    private WebElement birthPlaceNurse;

    @FindBy(xpath = "//input[6]")
    private WebElement fiscalCodeNurse;

    @FindBy(xpath = "//input[8]")
    private WebElement BottomRegistration;

    @FindBy(xpath = "//input[9]")
    private WebElement BottomBackward;

    @FindBy(xpath = "//form[@action='/createNurse']//button[@id='createNurse']")
    private WebElement createNurse;

    public CreateNursePO(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomeNurse() { return this.titleHomeNurse.getText();}
    public void enterNameNurse(String nameNurse) {
        this.nameNurse.clear();
        this.nameNurse.sendKeys(nameNurse);
    }
    public void enterSurnameNurse(String surnameNurse) {
        this.surnameNurse.clear();
        this.surnameNurse.sendKeys(surnameNurse);
    }
    public void enterPasswordNurse(String passwordNurse) {
        this.passwordNurse.clear();
        this.passwordNurse.sendKeys(passwordNurse);
    }
    public void enterBirthNurse(String birthNurse) {
        this.birthNurse.clear();
        this.birthNurse.sendKeys(birthNurse);
    }
    public void enterBirthPlaceNurse(String birthPlaceNurse) {
        this.birthPlaceNurse.clear();
        this.birthPlaceNurse.sendKeys(birthPlaceNurse);
    }
    public void enterFiscalCodeNurse(String fiscalCodeNurse) {
        this.fiscalCodeNurse.clear();
        this.fiscalCodeNurse.sendKeys(fiscalCodeNurse);
    }
    public ManagerPageObject BottomRegistration() {
        this.BottomRegistration.click();
        return new ManagerPageObject(driver);
    }
    public ManagerPageObject BottomBackward() {
        this.BottomBackward.click();
        return new ManagerPageObject(driver);
    }
    public ManagerPageObject createNurse() {
        this.createNurse.click();
        return new ManagerPageObject(driver);
    }
}
