package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateAppointmentByReceptionist extends PageObject {

    @FindBy(xpath = "//h1")
    private WebElement titleModifyAppointment;

    @FindBy(xpath = "//table[@id='AppointmentTable']//button[@id='updateAppointment']") // è in ProfilePageReceptionist
    private WebElement modifyAppointment;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='confirm']") // è in UpdateAppointment
    private WebElement confirmModify;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='patientId']")   // è in UpdateAppointment
    private WebElement patientId;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='patientName']")   // è in UpdateAppointment
    private WebElement patientName;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='patientSurname']")    /// è in UpdateAppointment
    private WebElement patientSurname;

    @FindBy(xpath = "//select[@id='doctor']")    // in createAppointment
    private WebElement doctorOfPatient;

    @FindBy(xpath = "//select[@id='nurse']")    // in createAppointment
    private WebElement nurseOfPatient;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='date']")    // in createAppointment
    private WebElement appointmentDate;

    @FindBy(xpath = "//form[@action='/updateAppointmentFromPage']//input[@id='hour']")    // in createAppointment
    private WebElement appointmentHour;

    public UpdateAppointmentByReceptionist(WebDriver driver) { super(driver);}

    public String getTitleModifyAppointment() {
        return this.titleModifyAppointment.getText();
    }

    // Metodi per inserire i dati

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
    public void enterDoctorOfPatient() { this.doctorOfPatient.sendKeys("1"); }
    public void enterNurseOfPatient() {
        this.nurseOfPatient.sendKeys("1");
    }
    public void enterAppointmentDate(String appointmentDate) {
        this.appointmentDate.clear();
        this.appointmentDate.sendKeys(appointmentDate);
    }
    public void enterAppointmentHour(String appointmentHour) {
        this.appointmentHour.clear();
        this.appointmentHour.sendKeys(appointmentHour);
    }

    public ReceptionistPageObject modifyAppointment() {
        this.modifyAppointment.click();
        return new ReceptionistPageObject(driver);
    }
    public ReceptionistPageObject confirmModify() {
        this.confirmModify.click();
        return new ReceptionistPageObject(driver);
    }


}
