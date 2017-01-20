package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

public class MaterialsRequestOrderTemplatePage extends MaterialsRequestHomePage {

    //Header
    private final By headerObject = By.xpath("//h1");
    private final String pageHeader = "Order Template";

    public MaterialsRequestOrderTemplatePage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyHeaders() {
        driver.assertText(headerObject, pageHeader);
    }
}
