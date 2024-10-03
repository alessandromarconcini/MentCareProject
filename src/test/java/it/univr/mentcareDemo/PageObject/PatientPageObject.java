package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PatientPageObject extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleHomePatient;

    @FindBy(xpath = "//td[1]")
    private WebElement id;

    @FindBy(xpath = "//td[2]")
    private WebElement patientName;

    @FindBy(xpath = "//td[3]")
    private WebElement patientSurname;

    @FindBy(xpath = "//table//tbody//td[4]")
    private WebElement doctorOfThePatient;     // Da profilePagePatient per il check

    @FindBy(xpath = "//table//tbody//td[5]")
    private WebElement nurseOfThePatient;     // Da profilePagePatient per il check

    @FindBy(xpath = "//table//tbody//td[6]")
    private WebElement appointmentDate;      // Da profilePagePatient per il check

    @FindBy(xpath = "//table//tbody//td[7]")
    private WebElement appointmentHour;      // Da profilePagePatient per il check

    @FindBy(xpath = "//table//tbody//td[1]")
    private List<WebElement> tableAppointment;    // Da profilePagePatient per il check

    public PatientPageObject(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomePatient() { return this.titleHomePatient.getText();}

    public String getPatientName() {
        return this.patientName.getText();
    }

    public String getPatientSurname() {
        return this.patientSurname.getText();
    }

    public String getDoctorOfThePatient() {
        return this.doctorOfThePatient.getText();
    }

    public String getNurseOfThePatient() { return this.nurseOfThePatient.getText();}

    public String getAppointmentDate() { return this.appointmentDate.getText();}

    public String getAppointmentHour() { return this.appointmentHour.getText();}

    public String getIdAppointment() { return this.id.getText();}

}
