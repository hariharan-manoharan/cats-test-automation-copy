package net.fulcrum.Pages.Web.MaterialsRequest;

import junit.framework.Assert;
import net.fulcrum.Drivers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MaterialsRequestOrdersPage extends MaterialsRequestHomePage {

    protected final By filtersTitleObject = By.xpath("//b");

    //Search Boxes
    protected final By orderSearch = By.xpath("//input[@type='text']");

    //Buttons
    protected final By clearFiltersButton = By.xpath("(//button[@type='button'])[2]");

    //Dropdowns
    protected final By selectRequestingBUDropdown = By.xpath("");
    protected final By selectRequestingBUTitleObject = By.xpath("//label");
    protected final By selectFromBUDropdown = By.xpath("");
    protected final By selectFromBUTitleObject = By.xpath("//div[4]/label");

    //Columns
    protected final By orderCodeColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th/label");
    protected final By requestingLocationColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[2]/label");
    protected final By requestingBUColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[3]/label");
    protected final By fromLocationColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[4]/label");
    protected final By requestorColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[5]/label");
    protected final By statusColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[6]/label");
    protected final By shipByColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[7]/label");
    protected final By createdOnColumn = By.xpath("//table[@id='ordersTable']/thead/tr/th[8]/label");

    //Buttons
    protected final By searchButton = By.xpath("//td/div/button");

    //Validation
    public void verifyColumns() {
        driver.verifyTextExists("Order Code");
        driver.verifyTextExists("Requesting Location");
     //   driver.verifyTextExists("Requesting BU");
        driver.verifyTextExists("From Location");
        driver.verifyTextExists("Requestor");
        driver.verifyTextExists("Status");
        driver.verifyTextExists("Ship By");
        driver.verifyTextExists("Created On");
    }

    public void verifyFieldTitles() {
        driver.assertText(filtersTitleObject, "Filters:");
        driver.assertText(selectRequestingBUTitleObject, "Select Requesting BU:");
        driver.assertText(selectFromBUTitleObject, "Select From BU:");
    }

    //Click objects
    public void clickSearch() {
        driver.click(searchButton);
    }

    public void clickOrderCodeColumn() {
        driver.click(orderCodeColumn);
    }

    public void clickRequestingLocationColumn() {
        driver.click(requestingLocationColumn);
    }

    public void clickRequestingBUColumn() {
        driver.click(requestingBUColumn);
    }

    public void clickFromLocationColumn() {
        driver.click(fromLocationColumn);
    }

    public void clickRequestorColumn() {
        driver.click(requestorColumn);
    }

    public void clickStatusColumn() {
        driver.click(statusColumn);
    }

    public void clickShipByColumn() {
        driver.click(shipByColumn);
    }

    public void clickCreatedOnColumn() {
        driver.click(createdOnColumn);
    }

    public void selectOrder(String orderCode) {
        driver.clickLink(orderCode);
    }

    //Input
    public void enterOrderCode(String orderCode) {
        driver.sendText(orderSearch, orderCode);
    }

}
