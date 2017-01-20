package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class LocationStatuses extends DataForm {

    public LocationStatuses(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchLocked = By.name("LOCKED");
    protected By searchNettable = By.name("NETTABLE");
    protected By searchInstallStatus = By.name("INSTALLSTATUS");
    protected By searchInserviceStatus = By.name("INSERVICESTATUS");

    //Edit Tab Columns
    protected By editLockedCheckBox = By.cssSelector("input[name=\"LOCKED\"]");
    protected By editNettableCheckBox = By.cssSelector("input[name=\"NETTABLE\"]");
    protected By editInstallStatusCheckBox = By.cssSelector("input[name=\"INSTALLSTATUS\"]");
    protected By editInServiceStatusCheckBox = By.cssSelector("input[name=\"INSERVICESTATUS\"]");

    public void searchDescription(String desc) {
        driver.sendText(searchDescription, desc);
    }

    public void searchCity(String code) {
        driver.sendText(searchCodeComponent, code);
    }

    public void editDescription(String desc) {
        driver.sendText(editDescription, desc);
    }

    public void editCodeComponent(String code){
        driver.sendText(editCodeComponent, code);
    }


    public void clickLocked() {
        driver.click(editLockedCheckBox);
    }

    public void clickNettable() {
        driver.click(editNettableCheckBox);
    }

    public void clickInstallStatus() {
        driver.click(editInstallStatusCheckBox);
    }

    public void clickInServiceStatus() {
        driver.click(editInServiceStatusCheckBox);
    }

}
