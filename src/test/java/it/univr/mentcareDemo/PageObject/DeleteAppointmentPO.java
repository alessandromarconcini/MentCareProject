package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteAppointmentPO extends PageObject {

    @FindBy(xpath = "//form[@action='/deleteAppointment']//button[@id='deleteAppointment']") // è in ProfilePageReceptionist
    private WebElement deleteAppointment;

    public DeleteAppointmentPO(WebDriver driver) { super(driver);}

    public ReceptionistPageObject deleteAppointment() {
        this.deleteAppointment.click();
        return new ReceptionistPageObject(driver);
    }



}
