package net.fulcrum.Pages.Web.Requisitions;

import junit.framework.Assert;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class RequisitionsTypesPage {

    private WebBrowserDriver driver;

    protected By addTypeButton = By.id("add-type-button");
    protected By header = By.id("//ol/li");
    protected By deleteButton = By.linkText("Delete");


    public RequisitionsTypesPage(WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void validateTitle(WebBrowserDriver driver) {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions:", getTitle);
    }

    public void validatePage() {
        validateTitle(driver);
    }

    public void clickAddType() {
        driver.click(addTypeButton);
    }

    public void clickType(String name) {
        driver.click(By.linkText(name));
    }

    public void clickDelete() {
        driver.click(deleteButton);
    }


}

