package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchUserByManager extends PageObject{

    @FindBy(id = "search-bar")
    private WebElement searchBar;

    @FindBy(id = "search-button")
    private WebElement buttonToSearch;

    @FindBy(name = "research")
    private WebElement insertSurname;

    public SearchUserByManager(WebDriver driver) { super(driver);}

    public void clear() {
        searchBar.clear();
    }

    public void enterSurname(String insertSurname) {
        this.insertSurname.clear();
        this.insertSurname.sendKeys(insertSurname);
    }
    public SearchUserByManager searchBar() {
        this.searchBar.click();
        return new SearchUserByManager(driver);
    }
    public SearchUserByManager buttonToSearch() {
        this.buttonToSearch.click();
        return new SearchUserByManager(driver);
    }
}
