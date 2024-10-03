package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NursePageObject extends PageObject{

    @FindBy(xpath = "//h1")    // da ProfilePageNurse
    private WebElement titleHomeNurse;

    @FindBy(xpath = "//td[1]")
    private WebElement id;

    @FindBy(xpath = "//table//tbody//td[6]")    // da ProfilePageNurse
    private WebElement appointmentDate;

    @FindBy(xpath = "//table//tbody//td[7]")    // da ProfilePageNurse
    private WebElement appointmentHour;

    @FindBy(xpath = "//td[2]")
    private WebElement patientName;

    @FindBy(xpath = "//td[3]")
    private WebElement patientSurname;

    @FindBy(xpath = "//td[3]")
    private List<WebElement> patientSurnameList;

    @FindBy(xpath = "//table//tbody//td[5]")
    private WebElement doctorOfThePatient;

    @FindBy(xpath = "//table//tbody//td[1]")    // da ProfilePageNurse
    private List<WebElement> tableAppointment;

    public NursePageObject(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomeNurse() { return this.titleHomeNurse.getText();}

    public String getAppointmentDate() { return this.appointmentDate.getText();}

    public String getAppointmentHour() { return this.appointmentHour.getText();}

    public String getPatientName() {
        return this.patientName.getText();
    }

    public String getPatientSurname() {
        return this.patientSurname.getText();
    }

    public String getDoctorOfThePatient() {
        return this.doctorOfThePatient.getText();
    }


    public String getIdPatient() { return this.id.getText();}

    public String getAllAppointmentInTable(int index){
        return  this.tableAppointment.get(index).getText();
    }

    public int getNumOfUserInTable(){
        return this.patientSurnameList.size(); //restituisce il numero totale di utenti nella tabella di appuntamenti
    }

}
