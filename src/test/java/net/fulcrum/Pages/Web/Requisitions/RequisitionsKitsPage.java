package net.fulcrum.Pages.Web.Requisitions;

import junit.framework.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;

import net.fulcrum.Drivers.WebBrowserDriver;

public class RequisitionsKitsPage{

    private WebBrowserDriver driver;

    private String url;

    //Tabs
    private By myKitsTab =  By.linkText("My Kits");
    private By publicKitsTab = By.linkText("Public Kits");
    private By inactiveKitsTab = By.linkText("Inactive Kits");

    //Buttons
    private By findReplacePartButton = By.xpath("//button[@type='submit']");
    private By createButton = By.cssSelector("//div/a/span");
    private By searchButton = By.xpath("//div[@id='DataTables_Table_0_filter']/label/div");

    //Search
    private By searchTextField = By.xpath("//input[@type='search']");

    //URLs
    private static final String publicBomsURL = "requisitions/boms#publicBoms";
    private static final String myBomsURL = "requisitions/boms#myBoms";
    private static final String inactiveBomsURL = "requisitions/boms#myInactiveBoms";

    public RequisitionsKitsPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void validateTitle(WebDriver driver) {
        String getTitle = driver.getTitle();
        Assert.assertEquals("CATS Requisitions:", getTitle);
    }

    public void validatePage(WebDriver driver) {
        validateTitle(driver);
    }

    //Navigate Tabs
    public void clickPublicKitsTab() {
        driver.click(publicKitsTab);
        Assert.assertEquals(url + publicBomsURL, driver.returnURL());
    }

    public void clickMyKitsTab() {
        driver.click(myKitsTab);
        Assert.assertEquals(url + myBomsURL, driver.returnURL());
    }

    public void clickInactiveKitsTab() {
        driver.click(inactiveKitsTab);
        Assert.assertEquals(url + inactiveBomsURL, driver.returnURL());
    }

    //Search
    public void enterKitSearch(String search) {
        driver.sendText(searchTextField, search);
        driver.click(searchButton);
    }

}

