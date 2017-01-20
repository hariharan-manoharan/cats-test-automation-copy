package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

public class MaterialsRequestAllOrdersPage extends MaterialsRequestOrdersPage {

    //Headers
    private final String pageHeader = "All Orders";

    //Buttons
    private final By showMyOrdersButton = By.xpath("//td[3]/div/button");

    public MaterialsRequestAllOrdersPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyHeader() {
        driver.verifyTextExists(pageHeader);
    }

    //Click objects
    public void clickShowMyOrders() {
        driver.click(showMyOrdersButton);
    }
}
