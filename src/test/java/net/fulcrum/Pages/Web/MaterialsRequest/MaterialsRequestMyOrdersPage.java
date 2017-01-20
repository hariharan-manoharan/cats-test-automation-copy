package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

public class MaterialsRequestMyOrdersPage extends MaterialsRequestOrdersPage {

    //Headers
    private final String pageHeader = "My Orders";
    private final By headerObject = By.xpath("//h3");

    //Buttons
    private final By showAllOrdersButton = By.xpath("//td[3]/div/button");

    public MaterialsRequestMyOrdersPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Navigation
    public void navigate(){
        clickOrders();
       // verifyHeader();
    }

    //Validation
    public void verifyHeader() {
        driver.assertText(headerObject, pageHeader);
    }

    //Click objects
    public void clickShowAllOrders() {
        driver.click(showAllOrdersButton);
    }
}

