package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

public class MaterialsRequestEmailResubscribeHistoryPage extends MaterialsRequestModuleSettingsPage {

    //Search field
    private final By filterUsersSearchField = By.xpath("//input[@id='historyunsubscriber']");

    //Buttons
    private final By refreshButton = By.xpath("//button[@id='refreshunsubhistory']");

    //Columns
    private final By userNameColumn = By.xpath("//table[@id='unsubhistories']/thead/tr/th");
    private final By applicationColumn = By.xpath("//table[@id='unsubhistories']/thead/tr/th[2]");
    private final By unsubscribeDateColumn = By.xpath("//table[@id='unsubhistories']/thead/tr/th[3]");
    private final By resubscribeByColumn = By.xpath("//table[@id='unsubhistories']/thead/tr/th[4]");
    private final By resubscribeOnColumn = By.xpath("//table[@id='unsubhistories']/thead/tr/th[5]");

    public MaterialsRequestEmailResubscribeHistoryPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Click objects
    public void clickRefresh() {
        driver.click(refreshButton);
    }

    public void clickCheckbox(int num) {
        driver.clickCheckbox(num);
    }

    //Search
    public void enterSearchFilter(String filter) {
        driver.sendText(filterUsersSearchField, filter);
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(userNameColumn, "User name");
        driver.assertText(applicationColumn, "Application");
        driver.assertText(unsubscribeDateColumn, "Unsubcribe Date");
        driver.assertText(resubscribeByColumn, "Resubscribed By");
        driver.assertText(resubscribeOnColumn, "Resubscribed On");
    }

    //Navigation
    public void navigate() {
        clickEmailResubscribeHistory();
    }
}
