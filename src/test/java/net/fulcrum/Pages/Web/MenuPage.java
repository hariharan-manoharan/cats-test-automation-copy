package net.fulcrum.Pages.Web;

import junit.framework.Assert;
import org.openqa.selenium.WebElement;

import net.fulcrum.Drivers.WebBrowserDriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MenuPage {

    private WebBrowserDriver driver;
    private WebDriverWait wait;

    //Menu Objects
    protected By applicationMenuButton = By.cssSelector("button.navbar-btn.dropdown-toggle");
    protected By settingsButton = By.cssSelector("i.icon.ion-gear-a");
    protected By helpButton = By.cssSelector("a.icon-btn");
    protected By profileButton = By.cssSelector("i.icon.ion-person");
    protected By homeIcon = By.cssSelector("a.navbar-brand");

    //Application Option Objects
    protected By centerPointOption = By.linkText("CenterPoint");
    protected By advancedInventoryOption = By.linkText("Advanced Inventory");
    protected By queryBuilderOption = By.linkText("Query Builder");
    protected By planningOption = By.linkText("Planning");
    protected By requisitionsOption = By.linkText("Requisitions");

    //Profile Option Objects
    protected By logoutOption = By.linkText("Logout");

    //About Option Objects
    protected By aboutOption = By.linkText("About");
    protected By helpOption = By.linkText("Help");
    
    //Settings Options Objects
    protected By applicationsOption = By.linkText("Applications");
    protected By changeUserPasswordOption = By.linkText("Change User Password");
    
    
    protected By waitPageLoad = By.linkText("Wait");
    protected By closeButton = By.linkText("Close");
    protected By logoutButton = By.xpath("//a/button");

    public MenuPage (WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void clickApplicationMenu () {
        driver.click(applicationMenuButton);
    }

    public void selectCenterPointOption () {
        clickApplicationMenu();
        driver.click(centerPointOption);
    }

    public void selectAdvancedInventoryOption () {
        clickApplicationMenu();
        driver.click(advancedInventoryOption);
    }

    public void selectPlanningOption () {
        clickApplicationMenu();
        driver.click(planningOption);
    }

    public void selectRequisitionsOption () {
        clickApplicationMenu();
        driver.click(requisitionsOption);
    }

    public void selectQueryBuilderOption () {
        clickApplicationMenu();
        driver.click(queryBuilderOption);
    }

    public void clickHelpMenu() {
        driver.click(helpButton);
    }

    public void selectAboutOption() {
        clickHelpMenu();
        driver.click(aboutOption);
    }

    public void selectHelpOption() {
        driver.click(helpOption);
    }

    public void selectApplicationsOption() {
        clickSettingsMenu();
        driver.click(applicationsOption);
    }

    public void clickClose () {
        driver.click(closeButton);
    }

    public void clickSettingsMenu () {
        driver.click(settingsButton);
    }

    public void clickProfileMenu () {
        driver.click(profileButton);
    }

    public void clickHome () {
        driver.click(homeIcon);
    }


    public void legacyLogout ()  {
        driver.waitForObject(logoutButton, 30);
        driver.click(logoutButton);
    }

    public void logout ()  {
        clickProfileMenu();
        clickProfileMenu();
        clickProfileMenu();
        driver.click(logoutOption);
    }

    public void validateTitle(WebDriver driver, String title)
    {
        String getTitle = driver.getTitle();
        Assert.assertEquals(title, getTitle);
    }

    public void validateCSSTitle(WebElement title, String text)
    {
        Assert.assertEquals(title.getText(), text);
    }


}
