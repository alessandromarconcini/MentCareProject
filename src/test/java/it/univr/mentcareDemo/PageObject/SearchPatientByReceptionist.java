package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPatientByReceptionist extends PageObject {

    @FindBy(xpath = "//input[@id='search-bar2']")
    private WebElement searchBar;

    @FindBy(id = "search-button2")
    private WebElement buttomToSearch;

    @FindBy(name = "research")
    private WebElement insertSurname;

    public SearchPatientByReceptionist(WebDriver driver) { super(driver);}

    public void clear() {
        searchBar.clear();
    }

    public void enterSurname(String insertSurname) {
        this.insertSurname.clear();
        this.insertSurname.sendKeys(insertSurname);
    }
    public SearchPatientByReceptionist searchBar() {
        this.searchBar.click();
        return new SearchPatientByReceptionist(driver);
    }
    public SearchPatientByReceptionist buttomToSearch() {
        this.buttomToSearch.click();
        return new SearchPatientByReceptionist(driver);
    }

}
