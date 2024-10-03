package it.univr.mentcareDemo.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ManagerPageObject extends PageObject{

    @FindBy(xpath = "//h1")
    private WebElement titleHomeManager;

    @FindBy(xpath = "//table//tbody//td[1]")
    private List<WebElement> userId;

    @FindBy(xpath = "//table//tbody//td[2]")
    private List<WebElement> lastUserNameInTable;

    @FindBy(xpath = "//table//tbody//td[3]")
    private List<WebElement> lastUserSurnameInTable;

    @FindBy(xpath = "//table//tbody//td[5]")
    private List<WebElement> lastUserTypeInTable;

    @FindBy(xpath = "//table//tbody//td[4]")
    private List<WebElement> lastUserFiscalCodeInTable;


    @FindBy(xpath = "//table//tbody//td[1]")
    private List<WebElement> tableUser;



    public ManagerPageObject(WebDriver driver) { super(driver);}

    //Per verificare in che pagina mi trovo
    public String getTitleHomeManager() { return this.titleHomeManager.getText();}


    public String getLastUserNameInTable(){
        int lastIndex = this.lastUserNameInTable.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.lastUserNameInTable.get(lastIndex).getText();
    }

    public String getLastUserSurnameInTable(){
        int lastIndex = this.lastUserSurnameInTable.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.lastUserSurnameInTable.get(lastIndex).getText();
    }
    public String getLastUserFiscalCodeInTable(){
        int lastIndex = this.lastUserFiscalCodeInTable.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.lastUserFiscalCodeInTable.get(lastIndex).getText();
    }

    public String getLastUserTypeInTable(){
        int lastIndex = this.lastUserTypeInTable.size() - 1; //Altrimenti va fuori indice, si considera la posizione 0

        return  this.lastUserTypeInTable.get(lastIndex).getText();
    }

    public String getUserInTable(int index){
        return  this.lastUserFiscalCodeInTable.get(index).getText();
    }

    public int getNumOfUserInTable(){
        return this.lastUserSurnameInTable.size(); //restituisce il numero totale di utenti nella tabella di appuntamenti
    }

}
