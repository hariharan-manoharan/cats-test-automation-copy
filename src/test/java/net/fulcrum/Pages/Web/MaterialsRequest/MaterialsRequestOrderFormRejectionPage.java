package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class MaterialsRequestOrderFormRejectionPage extends MaterialsRequestModuleSettingsPage {

    //Messages
    private final String searchError = "There was an error performing your search. Please try again.";

    //Search field
    private final By rejectOrderSearchField = By.xpath("//input[@id='orderId']");

    //Buttons
    private final By searchButton = By.xpath("//form/div[2]/button");
    private final By clearButton = By.xpath("//form/div[2]/button[2]");

    public MaterialsRequestOrderFormRejectionPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Search
    public void searchRejectedOrder(String order) {
        driver.sendText(rejectOrderSearchField, order);
    }

    //Click
    public void clickSearch() {
        driver.click(searchButton);
    }

    public void clickClear() {
        driver.click(clearButton);
    }
}
