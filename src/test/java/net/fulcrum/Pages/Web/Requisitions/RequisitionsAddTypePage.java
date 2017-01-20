package net.fulcrum.Pages.Web.Requisitions;


import junit.framework.Assert;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class RequisitionsAddTypePage {

    private WebBrowserDriver driver;

    protected By nameTextField  = By.id("name");

    protected By descriptionTextField = By.id("desc-field");

    protected By prefixTextField = By.id("prefix-field");

    protected By activeCheckbox = By.id("active");

    protected By enforceBudgetCheckbox = By.id("enforceBudget");

    protected By installLocationRequiredCheckbox = By.id("installLocationRequired");

    protected By createLocationCheckbox = By.id("createLocation");

    protected By transferByInstallLocationCheckbox = By.id("transferByInstallLocation");

    protected By configurationRequiredCheckbox = By.id("configurationRequired");

    protected By fulfilledByPurchaseRequestOnlyCheckbox = By.id("purchaseReqOnly");

    protected By autoApproveLimitCheckbox = By.id("autoApprove");

    protected By autoApproveLimitTextField = By.id("autoApproveLimit");

    protected By installationTypeCheckbox1 = By.id("checkedInstallationTypeIds1");

    protected By installationTypeCheckbox2 = By.id("checkedInstallationTypeIds2");

    protected By destinationTypeCheckbox1 = By.id("checkedDestinationTypeIds1");

    protected By destinationTypeCheckbox2 = By.id("checkedDestinationTypeIds2");

    protected By sourceStatusCheckbox1 = By.id("checkedSourceStatusIds1");

    protected By adminSecurityProfileCanViewCheckbox = By.xpath("//input[@name='securityProfileAccessors[0].canView']");

    protected By adminSecurityProfileCanCreateCheckbox = By.xpath("//input[@name='securityProfileAccessors[0].canCreate']");

    protected By cancelButton = By.linkText("cancel");

    protected By submitButton = By.id("submit-button");

    protected By editButton = By.linkText("Edit");

    protected By copyButton = By.linkText("Copy");


    public RequisitionsAddTypePage(WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void validateTitle(WebBrowserDriver driver) {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions: Create Requisition Type", getTitle);
    }

    public void validatePage(WebBrowserDriver driver) {
        validateTitle(driver);
    }

    public void enterName(String name) {
        driver.sendText(nameTextField, name);
    }

    public void enterDescription(String desc) {
        driver.sendText(descriptionTextField, desc);
    }

    public void enterPrefix(String prefix) {

        driver.sendText(prefixTextField, prefix);
    }

    public void clickActive() {
        driver.click(activeCheckbox);
    }

    public void clickEnforceBudget() {
        driver.click(enforceBudgetCheckbox);
    }

    public void clickInstallLocationRequired() {
        driver.click(installLocationRequiredCheckbox);
    }

    public void clickCreateLocation() {
        driver.click(createLocationCheckbox);
    }

    public void clickTransferByInstallLocation() {
        driver.click(transferByInstallLocationCheckbox);
    }

    public void clickConfigurationRequired() {
        driver.click(configurationRequiredCheckbox);
    }

    public void clickFulfilledByPurchaseRequestOnly() {
        driver.click(fulfilledByPurchaseRequestOnlyCheckbox);
    }

    public void clickAutoApprove() {
        driver.click(autoApproveLimitCheckbox);
    }

    public void enterAutoApproveLimit(Integer limit) {
        driver.sendText(autoApproveLimitTextField, limit.toString(limit));
    }

    public void clickInstallationType1() {
        driver.click(installationTypeCheckbox1);
    }

    public void clickInstallationType2() {
        driver.click(installationTypeCheckbox2);
    }

    public void clickDestinationType1() {
        driver.click(destinationTypeCheckbox1);
    }

    public void clickDestinationType2() {
        driver.click(destinationTypeCheckbox2);
    }


    public void clickAdminCanView() {
        driver.click(adminSecurityProfileCanViewCheckbox);
    }

    public void clickAdminCanCreate() {
        driver.click(adminSecurityProfileCanCreateCheckbox);
    }

    public void clickCancel() {
        driver.click(cancelButton);
    }

    public void clickSubmit() {
        driver.click(submitButton);
    }

    public void clickCopy() { driver.click(copyButton);}

    public void clickEdit() { driver.click(editButton);}

    public void clickSourceStatusCheckbox1() {
        driver.click(sourceStatusCheckbox1);
    }
}

