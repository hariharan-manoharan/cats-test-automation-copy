package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;

public class MaterialsRequestHomePage {

    protected WebBrowserDriver driver;
    protected WebElement element;
    protected String url;

    private final String application = "catsmr";

    //Buttons
    protected final By createButton = By.xpath("//a[contains(text(),'Create')]");
    protected final By logo = By.cssSelector("a.navbar-brand");
    protected final By logoutButton = By.xpath("//div[@id='site']/div/div[3]/div/a/button");
    protected final By dashboardButton = By.xpath("//a[contains(text(),'Dashboard')]");
    protected final By adminButton = By.xpath("//a[contains(text(),'Admin')]");
    protected final By ordersButton = By.xpath("//a[contains(text(),'Orders')]");
    protected final By cartButton = By.xpath("//a[contains(text(),'Cart')]");
    protected final By orderSettingsButton = By.xpath("//a[contains(text(),'Order Settings')]");

    //Create options
    protected final By materialsRequestOption = By.xpath("//a[contains(text(),'Shop Local (MR)')]");
    protected final By excessManagementOption = By.xpath("//a[contains(text(),'Shop Nationwide (EM)')]");

    //Admin options
    protected final By emModuleSettingsOption = By.xpath("//a[contains(text(),'EM Module Settings')]");
    protected final By emServicesSettingsOption = By.xpath("//a[contains(text(),'EM Service Settings')]");
    protected final By emSurveySetupOption = By.xpath("//a[contains(text(),'EM Survey Setup')]");
    protected final By emSurveyDashboard = By.xpath("//a[contains(text(),'EM Survey Dashboard')]");

    //Order Settings options
    protected final By emailUnsubscribeOption = By.xpath("//a[contains(text(),'Email Unsubscribe')]");
    protected final By defaultOrderHeader = By.xpath("//a[contains(text(),'Default Order Header')]");

    protected final By myDashboardTitle = By.cssSelector("h1.page-header");

    //Validation
    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        //Assert.assertEquals("CATS Materials Request", getTitle);
        Assert.assertEquals("CATS Excess Management", getTitle);
    }

    public void refreshPage() {
        driver.wd.get(url + application);
    }

    public void validatePage () {
        validateTitle();
    }

    public void validateDashboardTitle() {
        driver.wd.findElement(myDashboardTitle).click();
    }

    public void validateAdmin() {
        driver.waitForObject(adminButton, 45);
    }

    //Click objects
    public void clickDashboard() {
        driver.click(dashboardButton);
    }

    public void clickAdmin() {
        driver.click(adminButton);
    }

    public void clickOrders() {
        driver.click(ordersButton);
    }

    public void clickCart() {
        driver.click(cartButton);
    }

    public void clickOrdersSettings() {
        driver.click(orderSettingsButton);
    }

    public void clickLogo() {
        driver.click(logo);
    }

    public void clickCreate() {
        driver.click(createButton);
    }

    public void clickEmailUnsubscribe() {
        driver.click(emailUnsubscribeOption);
    }

    public void selectEMModuleSettings() {
        driver.click(emModuleSettingsOption);
    }

    public void selectShopNationwide() {
        clickCreate();
        driver.click(excessManagementOption);
    }

    public void selectShopLocally() {
        clickCreate();
        driver.click(materialsRequestOption);
    }

}


