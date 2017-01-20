package net.fulcrum.Pages.Web.CenterPoint;

import net.fulcrum.Drivers.*;
import junit.framework.Assert;
import org.openqa.selenium.*;


public abstract class CenterPointHomePage  {
    
    protected WebBrowserDriver driver;
    protected WebElement element;

    private final String intitalHeader = "Welcome to CATS CenterPoint";

    protected By dataFormsLink = By.linkText("Data Forms");
    protected By legacyLink = By.linkText("Legacy");
    protected By clientFolder = By.partialLinkText("Client");
    protected By webReportsFolder = By.partialLinkText("Web Reports");
    protected By searchBar = By.id("navTreeSearch");

    public CenterPointHomePage(WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void validateTitle(WebDriver driver) {
        String getTitle = driver.getTitle().substring(0,16);
        Assert.assertEquals("CATS CenterPoint", getTitle);
    }

    public void validateLocationsTitle(WebDriver driver) {
        validateTitle(driver);
    }


    public void validatePage (WebDriver driver) {
        validateLocationsTitle(driver);
    }

    public void navigateToLegacy() {
        driver.click(legacyLink);
    }

    public void navigateToDataforms() {
        driver.click(dataFormsLink);
    }

    public void clickClient() {
        driver.click(clientFolder);
    }

    public void enterSearch(String text) {
        driver.sendText(searchBar, text);
    }


    public void  clickDataform(String form) {
        try {
            driver.click(By.partialLinkText(form), 30);
        } catch (NoSuchElementException ex)  {
            System.out.println("Error: Cannot find object to select " + form + " dataform");

        }
    }

}
