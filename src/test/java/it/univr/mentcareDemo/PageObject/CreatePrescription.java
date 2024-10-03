package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePrescription extends PageObject {

    @FindBy(xpath = "//h1")    // in createPrescription
    private WebElement titleCreatePrescription;

    @FindBy(xpath = "//form[@action='/createPrescription']//button[@id='newPrescription']") // è in ProfilePageDoctor
    private WebElement createPrescription;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//input[@id='savePrescription']") // è in CreatePrescription
    private WebElement registerNewPrescription;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//input[@id='patientId']")
    private WebElement patientId;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//select[@id='drug1']")    // in createAppointment
    private WebElement drug;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//input[@id='dose1']")    // in createAppointment
    private WebElement dose;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//input[@id='frequency1']")    // in createAppointment
    private WebElement frequency;

    @FindBy(xpath = "//form[@action='/createPrescriptionFromPage']//textarea[@id='textReport']")    // in createAppointment
    private WebElement textReport;

    public CreatePrescription(WebDriver driver) { super(driver);}

    public String getTitleCreatePrescription() {
        return this.titleCreatePrescription.getText();
    }

    public DoctorPageObject createPrescription() {
        this.createPrescription.click();
        return new DoctorPageObject(driver);
    }
    public DoctorPageObject registerNewPrescription() {
        this.registerNewPrescription.click();
        return new DoctorPageObject(driver);
    }
    public void enterPatientId(String patientId) {
        this.patientId.clear();
        this.patientId.sendKeys(patientId);
    }
    public void enterDose(String dose) {
        this.dose.clear();
        this.dose.sendKeys(dose);
    }
    public void enterDrug() {
        this.drug.sendKeys("2");
    }

    public void enterFrequency(String frequency) {
        this.frequency.clear();
        this.frequency.sendKeys(frequency);
    }
    public void enterTextReport(String textReport) {
        this.textReport.clear();
        this.textReport.sendKeys(textReport);
    }


}
