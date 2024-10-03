package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateReceptionistPO extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleHomeReceptionist;

    @FindBy(xpath = "//input[1]")  // si gurda il "name" dell'input per tutti
    private WebElement nameReceptionist;

    @FindBy(xpath = "//input[2]")
    private WebElement surnameReceptionist;

    @FindBy(xpath = "//input[3]")
    private WebElement passwordReceptionist;

    @FindBy(xpath = "//input[4]")
    private WebElement birthReceptionist;

    @FindBy(xpath = "//input[5]")
    private WebElement birthPlaceReceptionist;

    @FindBy(xpath = "//input[6]")
    private WebElement fiscalCodeReceptionist;

    @FindBy(xpath = "//input[8]")
    private WebElement BottomRegistration;

    @FindBy(xpath = "//input[9]")
    private WebElement BottomBackward;

    @FindBy(xpath = "//form[@action='/createReceptionist']//button[@id='createReceptionist']")
    private WebElement createReceptionist;

    public CreateReceptionistPO(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomeReceptionist() { return this.titleHomeReceptionist.getText();}
    public void enterNameReceptionist(String nameReceptionist) {
        this.nameReceptionist.clear();
        this.nameReceptionist.sendKeys(nameReceptionist);
    }
    public void enterSurnameReceptionist(String surnameReceptionist) {
        this.surnameReceptionist.clear();
        this.surnameReceptionist.sendKeys(surnameReceptionist);
    }
    public void enterPasswordReceptionist(String passwordReceptionist) {
        this.passwordReceptionist.clear();
        this.passwordReceptionist.sendKeys(passwordReceptionist);
    }
    public void enterBirthReceptionist(String birthReceptionist) {
        this.birthReceptionist.clear();
        this.birthReceptionist.sendKeys(birthReceptionist);
    }
    public void enterFiscalCodeReceptionist(String fiscalCodeReceptionist) {
        this.fiscalCodeReceptionist.clear();
        this.fiscalCodeReceptionist.sendKeys(fiscalCodeReceptionist);
    }
    public void enterBirthPlaceReceptionist(String birthPlaceReceptionist) {
        this.birthPlaceReceptionist.clear();
        this.birthPlaceReceptionist.sendKeys(birthPlaceReceptionist);
    }
    public ManagerPageObject BottomRegistration() {
        this.BottomRegistration.click();
        return new ManagerPageObject(driver);
    }
    public ManagerPageObject BottomBackward() {
        this.BottomBackward.click();
        return new ManagerPageObject(driver);
    }


    public ManagerPageObject createReceptionist() {
        this.createReceptionist.click();
        return new ManagerPageObject(driver);
    }
}
