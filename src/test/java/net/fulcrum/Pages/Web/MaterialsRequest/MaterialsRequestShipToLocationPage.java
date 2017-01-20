package net.fulcrum.Pages.Web.MaterialsRequest;

import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.*;

public class MaterialsRequestShipToLocationPage extends MaterialsRequestModuleSettingsPage {

    private String[] activeLocationTypes = {"NS NETWORK - VZW STAGING", "NV NETWORK VENDOR STAGING"};

    private String[] inactiveLocationTypes = {"N0 CELL SITE", "N1 NETWORK SWITCH", "N2 NETWORK MICROWAVE HUB",
            "N4 SMALL CELL", "N5 SMALL CELL NODE", "N6 NETWORK SPARES", "N8 NETWORK (AIT) CROSS DOCK",
            "NA NETWORK OFFICE", "NB REGIONAL REFURB EQ", "NC NETWORK CRAN", "ND NETWORK DATA CENTER",
            "NE NETWORK VZW EFEMTO", "NF NETWORK FIBER HUB", "NJ REFURB NF VENDOR STAGING",
            "NL CELL SITE - ALLTEL LEGACY FLE", "NM NETWORK MOBILE CELL SITE", "NP REPEATERS", "NR REPAIR AND RETURN",
            "NT NETWORK TECH VEHICLE", "NU NETWORK RTU'S", "NW NETWORK MOBILE CELL DEPLOY", "NX NETWORK SEARCH RING",
            "NY NY NETWORK LAB"};

    //Dropdown List
    private final By shipLocationTypesDropdown = By.xpath("//select[@id='description']");
    private final By shipLocationTypesTitleObject = By.xpath("//div[3]/div/div/form/fieldset/div[2]/div/label");
    private final String shipLocationTypesTitle = "Ship Location Types: ";

    //Buttons
    private final By saveButton = By.xpath("(//button[@type='button'])[3]");
    private final By deleteButton = By.xpath("//fieldset[2]/div/button");
    private final By yesButton = By.xpath("//div[3]/button[2]");
    private final By noButton = By.xpath("//div[3]/button");

    //Columns
    private final By shipLocationTypeColumn = By.xpath("//table[@id='locations']/thead/tr/th[2]");
    private final By codeComponentColumn = By.xpath("//table[@id='locations']/thead/tr/th[3]");
    private final By addedByColumn = By.xpath("//table[@id='locations']/thead/tr/th[4]");
    private final By addedOnColumn = By.xpath("//table[@id='locations']/thead/tr/th[5]");

    public MaterialsRequestShipToLocationPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Get
    public By getDeleteButton() {
        return deleteButton;
    }

    //Click objects
    public void clickSave() {
        driver.click(saveButton);
    }

    public void clickDelete() {
        driver.click(deleteButton);
    }

    public void clickYes() {
        driver.click(yesButton);
    }

    public void clickNo() {
        driver.click(noButton);
    }

    public void clickCheckbox(int num) {
        driver.clickCheckbox(num);
    }

    //Select Dropdown
    public void selectLocationType(String locationType) {
        verifyShipLocationTypesTitle();
        driver.selectDropDownValue(shipLocationTypesDropdown, locationType);
    }

    //Validation
    public void verifyColumns() {
        driver.assertText(shipLocationTypeColumn, "Ship Location Type");
        driver.assertText(codeComponentColumn, "Code Component");
        driver.assertText(addedByColumn, "Added By");
        driver.assertText(addedOnColumn, "Added On");
    }

    public void verifyShipLocationTypesTitle() {
        driver.assertText(shipLocationTypesTitleObject, shipLocationTypesTitle);
    }

    public void verifyInactiveLocationTypes() {
        for (int i = 0; i < inactiveLocationTypes.length; i++) {
            verifyDropdownType(inactiveLocationTypes[i]);
        }
    }

    public void verifyActiveLocationTypes() {
        for (int i = 0; i < activeLocationTypes.length; i++) {
            //activeLocationTypes[i]);
        }
    }

    public boolean verifyDropdownType(String type) {
        return driver.verifyDropdownList(shipLocationTypesDropdown, type);
    }


    //Actions
    public boolean deleteLocationType(String type) {
        verifyColumns();
        List<WebElement> locationTypes = driver.wd.findElements(By.xpath("//table[@id='locations']/tbody/tr/td"));

        int pointer = driver.findTableValueIndex(locationTypes, type, 1, 5);

        if (pointer == 0) {
            driver.click(By.xpath("//table[@id='locations']/tbody/tr/td/input"));
        } else if (pointer >= 1) {
            driver.click(By.xpath("//table[@id='locations']/tbody/tr[" + pointer + "]/td/input"));
        } else {
            System.out.println("Location Type not found");
            return false;
        }
        return true;
    }

    //navigation
    public void navigate() {
        clickAdmin();

     // selectEMModuleSettings();
        clickShipToLocation();
        verifyColumns();
    }
}
