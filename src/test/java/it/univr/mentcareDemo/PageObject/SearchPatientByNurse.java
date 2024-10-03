package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPatientByNurse extends PageObject {

    @FindBy(xpath = "//input[@id='research1']")
    private WebElement searchBar;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttomToSearch;

    @FindBy(xpath = "//input[@id='research1']")
    private WebElement insertSurname;

    public SearchPatientByNurse(WebDriver driver) { super(driver);}

    public void clear() {
        searchBar.clear();
    }

    public void enterSurname(String insertSurname) {
        this.insertSurname.clear();
        this.insertSurname.sendKeys(insertSurname);
    }
    public SearchPatientByDoctor searchBar() {
        this.searchBar.click();
        return new SearchPatientByDoctor(driver);
    }
    public SearchPatientByDoctor buttomToSearch() {
        this.buttomToSearch.click();
        return new SearchPatientByDoctor(driver);
    }

}
