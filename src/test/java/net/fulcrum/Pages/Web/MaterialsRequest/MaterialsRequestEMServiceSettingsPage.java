package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;


public class MaterialsRequestEMServiceSettingsPage extends MaterialsRequestHomePage {


    //Header
    private final String pageHeader = "EM Survey Setup";
    private final By headerObject = By.cssSelector("h1.ng-scope");

    //Services
    private final String emPendingApprovalReminderService = "EM PENDING APPROVAL REMINDER SERVICE";
    private final String emPendingFulfillmentReminderService = "EM PENDING FULFILLMENT REMINDER SERVICE";
    private final String emSurveyAlertService = "EM SURVEY ALERT SERVICE";

    //Columns
    private final By activeColumn = By.xpath("//label");
    private final By serviceNameColumn = By.xpath("//th[2]/label");
    private final By excludeWeekendsColumn = By.xpath("//center/label");
    private final By daysInStatusColumn = By.xpath("//th[4]/center/label");
    private final By daysBeforeNextAlertColumn = By.xpath("//th[5]/center/label");
    private final By daysAvailableColumn = By.xpath("//th[6]/center/label");
    private final By limitColumn = By.xpath("//th[7]/center/label");

    public MaterialsRequestEMServiceSettingsPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(activeColumn, "Active");
        driver.assertText(serviceNameColumn, "Service Name");
        driver.assertText(excludeWeekendsColumn, "Exclude Weekends?");
        driver.assertText(daysInStatusColumn, "Days In Status");
        driver.assertText(daysBeforeNextAlertColumn, "Days Before Next Alert");
        driver.assertText(daysAvailableColumn, "Days Available");
        driver.assertText(limitColumn, "Limit");
    }

    public void verifyTitle() {
        driver.assertText(headerObject, pageHeader);
    }

    public void verifyServices() {
        driver.assertText(By.xpath("//td[2]/div"), emPendingApprovalReminderService);
        driver.assertText(By.xpath("//tr[2]/td[2]/div"), emPendingFulfillmentReminderService);
        driver.assertText(By.xpath("//tr[3]/td[2]/div"), emSurveyAlertService);
    }

    //Click
    public void clickViewButton(int num){
        By viewButton;

        if (num > 1) {
            viewButton = By.xpath("(//tr[" + num + "]td[9]/button");
        } else {
            viewButton = By.xpath("(//td[9]/button");
        }

        driver.click(viewButton);
    }

    public void clickEditButton(int num){
        By editButton;

        if (num > 1) {
            editButton = By.xpath("(//tr[" + num + "]td[10]/button");
        } else {
            editButton = By.xpath("(//td[10]/button");
        }

        driver.click(editButton);
    }
}
