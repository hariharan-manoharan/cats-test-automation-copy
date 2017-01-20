package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class BusinessUnits extends DataForm {

    public BusinessUnits(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchName = By.name("Z.NAME");
    protected By searchDescription = By.name("Z.DESCRIPTION");
    protected By searchParentBusinessUnit = By.name("PZ.NAME");
    protected By searchBaseCurrencyCode = By.name("BU.BASECURRENCYCODE");
    protected By searchAddedBy = By.name("Z.ADDCONTACTID");
    protected By searchModifedBy = By.name("Z.MODIFIEDCONTACTID");

    //Edit Tab Columns
    protected By editName = By.name("NAME");
    protected By editDescription = By.name("DESCRIPTION");
    protected By editParentBusinessUnit = By.name("PARENTBU");
    protected By editBaseCurrencyCode = By.name("BASECURRENCYCODE");

    public void searchName(String name) {
        driver.sendText(searchName, name);
    }

    public void searchDescription(String desc) {
        driver.sendText(searchDescription, desc);
    }

    public void searchParentBusinessUnit(String bu) {
        driver.sendText(searchParentBusinessUnit, bu);
    }

    public void searchBaseCurrencyCode(String code){
        driver.sendText(searchBaseCurrencyCode, code);
    }

    public void searchAddedby(String addedBy) {
        driver.sendText(searchAddedBy, addedBy);
    }

    public void searchModifiedBy(String modifiedBy) {
        driver.sendText(searchModifedBy, modifiedBy);
    }

    public void editName(String name) {
        driver.sendText(editName, name);
    }

    public void editDescription(String desc) {
        driver.sendText(editDescription, desc);
    }

    public void editParentBusinessUnit(String bu) {
        driver.sendText(editParentBusinessUnit, bu);
    }

    public void editBaseCurrencyCode(String code) {
        driver.sendText(editBaseCurrencyCode, code);
    }
}
