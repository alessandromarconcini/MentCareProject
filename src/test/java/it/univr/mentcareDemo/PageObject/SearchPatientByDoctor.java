package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPatientByDoctor extends PageObject {

    @FindBy(id = "search-bar3")
    private WebElement searchBar;

    @FindBy(id = "search-button3")
    private WebElement buttomToSearch;

    @FindBy(name = "research")
    private WebElement insertSurname;

    public SearchPatientByDoctor(WebDriver driver) { super(driver);}

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
