package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.testng.Assert;

import java.util.List;

public class MaterialsRequestCartPage extends MaterialsRequestHomePage {

    //Dropdown lists
    private final By shipMethodDropDown = By.xpath("//div[2]/div/select");
    private final By shipLocationBUDropDown = By.xpath("//select");

    //Textfields
    private final By shipLocationTextField = By.name("locations");
    private final By siteContactTextField = By.xpath("(//input[@type='text'])[3]");
    private final By notesTextField = By.xpath("//p/textarea");
    private final By dateTextField = By.id("needByDate");

    //Headers
    private final String shippingHeader = "Shipping Information";
    private final String additionalShippingHeader = "Additional Shipping Information";
    private final By shippingHeaderObject = By.xpath("//div[3]/div[3]/div/div");
    private final By additionalShippingHeaderObject = By.xpath("//div[4]/div/div");

    //Buttons
    private final By topCancelButton = By.xpath("//div[4]/div/a/button");
    private final By bottomCancelButton = By.xpath("//div[6]/div/a/button");
    private final By submitButton = By.xpath("//a[2]/button");
    private final By okButton = By.xpath("//center/span");

    //Datepicker
    private final By needByDate = By.xpath("//input[@id='needByDate']");

    //Columns
    private final By partCodeColumn = By.xpath("//th/label");
    private final By descriptionColumn = By.xpath("//th[2]/label");
    private final By manufacturerColumn = By.xpath("//th[3]/label");
    private final By categoryColumn = By.xpath("//th[4]/label");
    private final By typeColumn = By.xpath("//th[5]/label");
    private final By costColumn = By.xpath("//th[6]/label");
    private final By requestedColumn = By.xpath("//th[7]/label");

    //Messages
    private final By emptyCartObject = By.xpath("//div[3]/div[2]");
    private final By invalidQtyPopupObject = By.xpath("//h4");
    private final String emptyCartMessage = "There are currently no items in your cart!";
    private final String processingOrderMessage = "REQUEST IS BEING PROCESSED. ORDERS ARE BEING CREATED.";
    private final String invalidQtyMessage = "YOU ARE TRYING TO ADD MORE QUANTITY TO REQUEST THAN IS AVAILABLE OR " +
            "ANOTHER REQUEST HAS RESERVED THE PARTS.";

    public MaterialsRequestCartPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyHeaders() {
        driver.assertText(shippingHeaderObject, shippingHeader);
        driver.assertText(additionalShippingHeaderObject, additionalShippingHeader);
    }

    public void verifyEmptyCart() {
        driver.assertText(emptyCartObject, emptyCartMessage);
    }

    public void verifyInvalidQtyMessage() {
        driver.assertText(invalidQtyPopupObject, invalidQtyMessage);
    }

    //Click Objects
    public void clickTopCancelButton() {
        driver.click(topCancelButton);
    }

    public void clickBottomCancelButton() {
        driver.click(bottomCancelButton);
    }

    public void clickSubmitButton() {
        driver.clickButton(submitButton, 45);
    }

    //Pick Date
    public void selectDate() {
    }

    //Enter Text
    public void enterRequiredData(String shipMethod, String shipLocation, String date, String shipLocationBU,
                                  String siteContact) {
        selectShipMethodDropdown(shipMethod);
        enterShipLocation(shipLocation);
        enterDate(date);
        verifyHeaders();
        driver.verifyTextExists(shipLocationBU);
        enterSiteContact(siteContact);
    }

    public void enterShipLocation(String shipLocation) {
        //driver.sendText(shipLocationTextField, shipLocation);
        driver.wd.findElement(By.id("shipmentInfo.location")).clear();
        driver.wd.findElement(By.id("shipmentInfo.location")).sendKeys("SEATTLE");
        driver.click(By.xpath("//div/div/div/div/ul/li/a/span"));

    }

    public void enterSiteContact(String siteContact) {
        driver.sendText(siteContactTextField, siteContact);
    }

    public void enterDate(String date) {
        driver.sendText(dateTextField, date);
    }

    public void enterNotes(String notes) {
        driver.sendText(notesTextField, notes);
    }

    //Select dropdown
    public void selectShipMethodDropdown(String method) {
        driver.selectDropDownValue(shipMethodDropDown, method);
    }

    public void selectLocationBUDropdown(String BU) {
        driver.selectDropDownValue(shipLocationBUDropDown, BU);
    }

    public boolean updateQty(String partCode, int qty) {
        List<WebElement> availableItems = driver.wd.findElements(By.xpath("//body[@id='ng-app']/div[3]/div[2]/div[2]/form/div[5]/table/tbody/tr/td/div"));
        int index = driver.findTableValueIndex(availableItems, partCode, 0, 6);

        if (index == 0) {
            driver.sendText(By.xpath("//input[@type='number']"), String.valueOf(qty));
            driver.click(By.xpath("//div/span/button"));
        } else if (index >= 1) {
            driver.sendText(By.xpath("(//input[@type='number'])[" + index + "]"), String.valueOf(qty));
            driver.click(By.xpath("(//tr[" + index + "]/td[7]/div/span/button)"));
        } else {
            System.out.println("Part Code not found");
            return false;
        }
        return true;
    }

    public void navigate() {
        clickCart();
    }

}
