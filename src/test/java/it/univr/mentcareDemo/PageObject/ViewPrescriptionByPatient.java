package it.univr.mentcareDemo.PageObject;

import it.univr.mentcareDemo.model.Patient;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ViewPrescriptionByPatient extends PageObject{
    @FindBy(xpath = "//h1")
    private WebElement titlePatientPrescription;

    @FindBy(xpath = "//table[@id='tableDrug']//tbody//td[1]")
    private List<WebElement> drugName;

    @FindBy(xpath = "//table[2]//tbody//td[2]")
    private List<WebElement> drugMinimumDose;

    @FindBy(xpath = "//table[2]//tbody//td[3]")
    private List<WebElement> drugMaximumDose;

    @FindBy(xpath = "//table[2]//tbody//td[4]")
    private List<WebElement> drugAssignedDose;

    @FindBy(xpath = "//table[2]//tbody//td[5]")
    private List<WebElement> drugFrequency;

    @FindBy(xpath = "//td[@id='prescription']")
    private WebElement textPrescription;

    @FindBy(xpath = "//td[8]")
    private WebElement viewPrescription;

    @FindBy(xpath = "//button[@id='back']")
    private WebElement backword;
    public ViewPrescriptionByPatient(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitlePatientPrescription() { return this.titlePatientPrescription.getText();}

    public String getTextPrescription() { return this.textPrescription.getText();}

    public String getDrugName() {
        int lastIndex = this.drugName.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.drugName.get(lastIndex).getText();
    }

    public String getDrugAssignedDose(){
        int lastIndex = this.drugAssignedDose.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.drugAssignedDose.get(lastIndex).getText();
    }

    public String getDrugFrequency(){
        int lastIndex = this.drugFrequency.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.drugFrequency.get(lastIndex).getText();
    }

    public PatientPageObject viewPrescription() {
        this.viewPrescription.click();
        return new PatientPageObject(driver);
    }

    public PatientPageObject backword() {
        this.backword.click();
        return new PatientPageObject(driver);
    }
}
