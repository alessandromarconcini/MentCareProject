package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class CreateAppointmentByReceptionist extends PageObject {

    @FindBy(xpath = "//h1")    // in createAppointment
    private WebElement titleCreateAppointment;

    @FindBy(xpath = "//form[@action='/createAppointment']//button[@id='newAppointment']") // è in ProfilePageReceptionist
    private WebElement createAppointment;

    @FindBy(xpath = "//form[@action='/createAppointmentFromPage']//input[@id='newAppointment']") // è in CreateAppointment
    private WebElement registerNewAppointment;

    @FindBy(xpath = "//input[1]")
    private WebElement patientId;

    @FindBy(xpath = "//form[@action='/createAppointmentFromPage']//input[@id='patientName']")    // in createAppointment
    private WebElement patientName;

    @FindBy(xpath = "//form[@action='/createAppointmentFromPage']//input[@id='patientSurname']")    // in createAppointment
    private WebElement patientSurname;

    @FindBy(xpath = "//select[@name='doctorSurname']")    // in createAppointment
    private WebElement doctorOfPatient;//TODO errore per select, non capisco come selezionare il campo dal menù a tendina

    @FindBy(xpath = "//select[@id='nurseSurname']")    // in createAppointment
    private WebElement nurseOfPatient;

    @FindBy(xpath = "//form[@action='/createAppointmentFromPage']//input[@id='date']")    // in createAppointment
    private WebElement appointmentDate;

    @FindBy(xpath = "//form[@action='/createAppointmentFromPage']//input[@id='hour']")    // in createAppointment
    private WebElement appointmentHour;

    public CreateAppointmentByReceptionist(WebDriver driver) { super(driver);}

    public String getTitleCreateAppointment() { return this.titleCreateAppointment.getText();}

    public ReceptionistPageObject createAppointment() {
        this.createAppointment.click();
        return new ReceptionistPageObject(driver);
    }
    public ReceptionistPageObject registerNewAppointment() {
        this.registerNewAppointment.click();
        return new ReceptionistPageObject(driver);
    }
    public void enterPatientId(String patientId) {
        this.patientId.clear();
        this.patientId.sendKeys(patientId);
    }
    public void enterPatientName(String patientName) {
        this.patientName.clear();
        this.patientName.sendKeys(patientName);
    }
    public void enterPatientSurname(String patientSurname) {
        this.patientSurname.clear();
        this.patientSurname.sendKeys(patientSurname);
    }
    public void enterAppointmentDate(String appointmentDate) {
        this.appointmentDate.clear();
        this.appointmentDate.sendKeys(appointmentDate);
    }
    public void enterAppointmentHour(String appointmentHour) {
        this.appointmentHour.clear();
        this.appointmentHour.sendKeys(appointmentHour);
    }
    public void enterDoctorOfPatient() {
        this.doctorOfPatient.sendKeys("1");
    }
    public void enterNurseOfPatient() {
        this.nurseOfPatient.sendKeys("1");
    }
}
