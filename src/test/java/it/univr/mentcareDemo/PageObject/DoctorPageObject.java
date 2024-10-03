package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DoctorPageObject extends PageObject{

    @FindBy(xpath = "//h1")   // da profilePageDoctor
    private WebElement titleHomeDoctor;

    @FindBy(xpath = "//td[1]")  // Dalla table del manager
    private WebElement idPatient;

    @FindBy(xpath = "//td[2]")  // Dalla table del manager
    private WebElement idAppointment;

    @FindBy(xpath = "//table//tbody//td[1]")
    private List<WebElement> tableAppointment; //AppointmentId // da profilePageDoctor

    @FindBy(xpath = "//table[@id='AppointmentTable2']//tbody//tr")
    private List<WebElement> selectRow;

    @FindBy(xpath = "//table//tbody//td[7]")    // da ProfilePageNurse
    private WebElement appointmentDate;

    @FindBy(xpath = "//table//tbody//td[8]")    // da ProfilePageNurse
    private WebElement appointmentHour;

    @FindBy(xpath = "//td[3]")
    private WebElement patientName;

    @FindBy(xpath = "//td[4]")
    private WebElement patientSurname;

    @FindBy(xpath = "//td[4]")
    private List<WebElement> patientSurnameList;

    @FindBy(xpath = "//td[6]")
    private WebElement nurseSurname;

    public DoctorPageObject(WebDriver driver) { super(driver);}

    public String getTitleHomeDoctor() { return this.titleHomeDoctor.getText();}

    public String getLastAppointmentInTable(){
        int lastIndex = this.tableAppointment.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.tableAppointment.get(lastIndex).getText();
    }

    public String getPatientId() { return this.idPatient.getText();}

    public String getIdAppointment() { return this.idAppointment.getText();}

    public String getPatientName() { return this.patientName.getText();}

    public String getPatientSurname() { return this.patientSurname.getText();}

    public String getAppointmentDate() { return this.appointmentDate.getText();}

    public String getAppointmentHour() { return this.appointmentHour.getText();}

    public String getNurseSurname() { return this.nurseSurname.getText();}

    public DoctorPageObject selectRow(){
        this.selectRow.get(0).click();
        return new DoctorPageObject(driver);
    }
    public String getAllAppointmentInTable(int index){
        return  this.tableAppointment.get(index).getText();
    }

    public int getNumOfUserInTable(){
        return this.patientSurnameList.size(); //restituisce il numero totale di utenti nella tabella di appuntamenti
    }

}
