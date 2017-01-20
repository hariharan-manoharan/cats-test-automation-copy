package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

public class MaterialsRequestOrderPriorityHistoryPage extends MaterialsRequestModuleSettingsPage {

    //Messages
    private final String noOrderPriorityHistoryRecords = "There are currently no records in the order priority history.";

    //Search field
    private final By filterUsersSearchField = By.xpath("//input[@id='historyunsubscriber']");

    //Buttons
    private final By refreshButton = By.xpath("//button[@id='refreshpriorityhistory']");

    //Columns
    private final By firstOrderPriorityColumn = By.xpath("//table[@id='priorities']/thead/tr/th");
    private final By secondOrderPriorityColumn = By.xpath("//table[@id='priorities']/thead/tr/th[2]");
    private final By thirdOrderPriorityColumn = By.xpath("//table[@id='priorities']/thead/tr/th[3]");
    private final By fourthOrderPriorityColumn = By.xpath("//table[@id='priorities']/thead/tr/th[4]");
    private final By addedOnColumn = By.xpath("//table[@id='priorities']/thead/tr/th[5]");
    private final By modifiedByColumn = By.xpath("//table[@id='priorities']/thead/tr/th[6]");
    private final By modifiedOnColumn = By.xpath("//table[@id='priorities']/thead/tr/th[7]");

    public MaterialsRequestOrderPriorityHistoryPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(firstOrderPriorityColumn, "First Order Priority");
        driver.assertText(secondOrderPriorityColumn, "Second Order Priority");
        driver.assertText(thirdOrderPriorityColumn, "Third Order Priority");
        driver.assertText(fourthOrderPriorityColumn, "Fourth Order Priority");
        driver.assertText(addedOnColumn, "Added On");
        driver.assertText(modifiedByColumn, "Modified By");
        driver.assertText(modifiedOnColumn, "Modified On");
    }

    //Navigation
    public void navigate() {
        clickOrderPriorityHistory();
    }
}
