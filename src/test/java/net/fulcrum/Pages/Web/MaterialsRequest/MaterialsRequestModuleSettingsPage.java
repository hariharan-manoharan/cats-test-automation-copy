package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;

public class MaterialsRequestModuleSettingsPage extends MaterialsRequestHomePage {

    //Header
    private final By title = By.cssSelector("h1.ng-scope");

    //Tabs
    private final By emailResubscribeTab = By.xpath("//a[contains(text(),'Email Resubscribe')]");
    private final By roleAssignmentTab = By.xpath("//a[contains(text(),'Role Assignment')]");
    private final By shipToLocationTab = By.xpath("//a[contains(text(),'Ship To Location')]");
    private final By orderPriorityTab = By.xpath("//a[contains(text(),'Order Priority')]");
    private final By emailResubscribeHistoryTab = By.xpath("//a[contains(text(),'Email Resubscribe History')]");
    private final By orderPriorityHistoryTab = By.xpath("//a[contains(text(),'Order Priority History')]");
    private final By orderFromRejection = By.xpath("//a[contains(text(),'Order From Rejection')]");

    public void clickShipToLocation() {
        driver.click(shipToLocationTab);
    }

    public void clickRoleAssignment() {
        driver.click(roleAssignmentTab);
    }

    public void clickEmailResubscribe() {
        driver.click(emailResubscribeTab );
    }

    public void clickEmailResubscribeHistory() {
        driver.click(emailResubscribeHistoryTab );
    }

    public void clickOrderPriorityHistory() {
        driver.click(orderPriorityHistoryTab);
    }



    public void verifyTabs() {
        driver.waitForObject(emailResubscribeTab, 45);
        driver.waitForObject(roleAssignmentTab, 45);
        driver.waitForObject(shipToLocationTab, 45);
        driver.waitForObject(orderPriorityTab, 45);
        driver.waitForObject(emailResubscribeHistoryTab, 45);
        driver.waitForObject(orderPriorityTab, 45);
        driver.waitForObject(orderFromRejection, 45);
    }
}
