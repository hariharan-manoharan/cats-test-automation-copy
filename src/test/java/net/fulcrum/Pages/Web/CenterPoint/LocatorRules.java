package net.fulcrum.Pages.Web.CenterPoint;

import org.openqa.selenium.By;
import net.fulcrum.Drivers.*;

public class LocatorRules extends DataForm {

    public LocatorRules(WebBrowserDriver driver) {
        super(driver);
    }

    //Search Tab Columns
    protected By searchLocator = By.name("R.NAME");
    protected By searchLocatorExpression = By.name("R.LOCATOR_REGEX");
    protected By searchDefinition = By.name("R.DEFINITION");
    protected By searchAddedBy = By.name("R.ADDCONTACTID");
    protected By searchModifedBy = By.name("R.MODIFIEDCONTACTID");

    //Edit Tab Columns
    protected By editLocator = By.name("NAME");
    protected By editLocatorExpression = By.name("LOCATOR_REGEX");
    protected By editDefinition= By.name("DEFINITION");

    public void searchLocator(String locator) {
        driver.sendText(searchLocator, locator);
    }

    public void searchLocatorExpression(String expression) {
        driver.sendText(searchLocatorExpression, expression);
    }

    public void searchDefinition(String definition) {
        driver.sendText(searchDefinition, definition);
    }

    public void searchAddedby(String addedBy) {
        driver.sendText(searchAddedBy, addedBy);
    }

    public void searchModifiedBy(String modifiedBy) {
        driver.sendText(searchModifedBy, modifiedBy);
    }

    public void editLocator(String locator) {
        driver.sendText(editLocator, locator);
    }

    public void editLocatorExpression(String expression) {
        driver.sendText(editLocatorExpression, expression);
    }

    public void editDefinition(String definition) {
        driver.sendText(editDefinition, definition);
    }

}
