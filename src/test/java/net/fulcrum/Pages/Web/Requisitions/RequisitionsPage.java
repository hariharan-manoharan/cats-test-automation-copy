package net.fulcrum.Pages.Web.Requisitions;


import junit.framework.Assert;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;


public class RequisitionsPage {

    protected WebBrowserDriver driver;
    protected By element;
    protected String url;

    private final String application = "requisitions";

    private By approveButton = By.id("approve");
    private By rejectButton = By.id("reject");
    private By confirmRejectButton = By.id("confirmReject");

    public RequisitionsPage (WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions: Dashboard", getTitle);
    }

    public void refreshPage() {
        driver.wd.get(url + application);
    }

    public void validatePage () {
        validateTitle();
    }

    public void clickApproveButton() {
        driver.click(approveButton);
    }

    public void clickRejectButton() {
        driver.click(rejectButton);
    }

    public void clickConfirmRejectButton() {
        driver.click(confirmRejectButton);
    }

}
