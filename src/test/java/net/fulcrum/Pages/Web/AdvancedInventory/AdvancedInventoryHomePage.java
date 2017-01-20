package net.fulcrum.Pages.Web.AdvancedInventory;

import net.fulcrum.Drivers.*;
import junit.framework.Assert;
import org.openqa.selenium.*;


public class AdvancedInventoryHomePage {

    protected WebBrowserDriver driver;
    protected WebElement element;

    private final By proposalsIcon = By.cssSelector("i.glyphicon.glyphicon-inbox");
    private final By inventoryIcon = By.cssSelector("i.glyphicon.glyphicon-list-alt");
    private final By newRecordTextBox = By.name("name");
    private final By createProposalButton = By.xpath("(//button[@type='submit'])[5]");

    public AdvancedInventoryHomePage (WebBrowserDriver driver) {
        this.driver = driver;

    }

    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Advanced Inventory:", getTitle);
    }

    public void clickProposalsIcon() {
        driver.click(proposalsIcon);
    }

    public void clickInventoryIcon() {
        driver.click(inventoryIcon);
    }

    public void validatePage () {
    }

    public void enterNewRecord(String text) {
        driver.sendText(newRecordTextBox, text);
    }

    public void clickCreateProposal() {
        driver.click(createProposalButton);
    }


}
