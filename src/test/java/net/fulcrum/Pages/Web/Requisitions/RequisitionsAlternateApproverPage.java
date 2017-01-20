package net.fulcrum.Pages.Web.Requisitions;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;
import org.testng.Assert;

public class RequisitionsAlternateApproverPage {

    protected WebBrowserDriver driver;
    protected By element;
    protected String url;

    private final String page = "requisitions/alternates";

    //Button Objects
    protected By updateButton = By.xpath("//button[@type='submit']");
    protected By resetButton = By.xpath("//button[@type='reset']");

    //Text Objects
    protected By alternateApproverTextField = By.id("alternateName");
    protected By successfulMessagePop = By.cssSelector("div.alert.alert-info");
    protected By errorMessagePopup = By.cssSelector("div.alert.alert-danger");

    //Messages
    private final String invalidContactUpdateMessaage = "Unable to find a contact with that name";
    private final String sameContactUpdateMessage = "No changes were made because the new alternate is the same as the " +
            "existing alternate";
    private final String newContactUpdateMessage = "Alternate approver updated successfully.";


    public RequisitionsAlternateApproverPage (WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }


    public void refreshPage() {
        driver.wd.get(url + page);
    }

    //Validations
    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions: Alternate Approver", getTitle);
    }

    public void validateInvalidUserUpdate() {
        Assert.assertEquals(invalidContactUpdateMessaage, driver.getElement(errorMessagePopup).getText());
    }

    public void validateSameUserUpdate() {
        Assert.assertEquals(sameContactUpdateMessage, driver.getElement(successfulMessagePop).getText());
    }

    public void validateNewUserUpdate() {
        Assert.assertEquals(newContactUpdateMessage, driver.getElement(successfulMessagePop).getText());
    }

    public void validateReset(String contact) {
        Assert.assertEquals(contact, driver.getElement(alternateApproverTextField).getAttribute("value"));
    }

    //Buttons
    public void clickUpdateButton() {
        driver.click(updateButton);
    }

    public void clickResetButton() {
        driver.click(resetButton);
    }

    //Text
    public void enterAlternateApprover(String contact) {
        driver.sendText(alternateApproverTextField, contact);
    }


}
