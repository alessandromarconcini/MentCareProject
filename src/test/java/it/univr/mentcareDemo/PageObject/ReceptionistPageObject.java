package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ReceptionistPageObject extends PageObject{

    @FindBy(xpath = "//h1")   // da ProfilePageReceptionist
    private WebElement titleHomeReceptionist;

    @FindBy(xpath = "//td[1]")   // nella table del Manager
    private WebElement id;

    @FindBy(xpath = "//table//tbody//td[6]")   // da ProfilePageReceptionist
    private List<WebElement> appointmentDate;

    @FindBy(xpath = "//table//tbody//td[7]")    // da ProfilePageReceptionist
    private List<WebElement> appointmentHour;

    @FindBy(xpath = "//table//tbody//td[4]")    // da ProfilePageReceptionist
    private List<WebElement> doctorOfPatient;

    @FindBy(xpath = "//table//tbody//td[5]")    // da ProfilePageReceptionist
    private List<WebElement> nurseOfPatient;

    @FindBy(xpath = "//table//tbody//td[2]")    // da ProfilePageReceptionist
    private List<WebElement> patientId;

    @FindBy(xpath = "//table//tbody//td[3]")    // da ProfilePageReceptionist
    private List<WebElement> patientSurname;

    @FindBy(xpath = "//table//tbody//td[1]")    // da ProfilePageReceptionist
    private List<WebElement> tableAppointment;


    public ReceptionistPageObject(WebDriver driver) { super(driver);}

    public String getTitleHomeReceptionist() { return this.titleHomeReceptionist.getText();}

    public String getTableAppointmentDate(){
        int lastIndex = this.appointmentDate.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.appointmentDate.get(lastIndex).getText();
    }

    public String getTablePatientId(){
        int lastIndex = this.patientId.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.patientId.get(lastIndex).getText();
    }
    public String getTableAppointmentHour(){
        int lastIndex = this.appointmentHour.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.appointmentHour.get(lastIndex).getText();
    }
    public String getTableNurseOfPatient(){
        int lastIndex = this.nurseOfPatient.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.nurseOfPatient.get(lastIndex).getText();
    }
    public String getTableDoctorOfPatient(){
        int lastIndex = this.doctorOfPatient.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.doctorOfPatient.get(lastIndex).getText();
    }
    public String getTablePatientSurname(){
        int lastIndex = this.patientSurname.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.patientSurname.get(lastIndex).getText();
    }

    public int getLastAppointmentSizeInTable(){
        return this.tableAppointment.size();
    }

    public String getAllAppointmentInTable(int index){
        return  this.tableAppointment.get(index).getText();
    }

    public int getNumOfUserInTable(){
        return this.patientSurname.size(); //restituisce il numero totale di utenti nella tabella di appuntamenti
    }

}
