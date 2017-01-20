package net.fulcrum.Pages.Web.MaterialsRequest;
import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MaterialsRequestShopNationwidePage  extends  MaterialsRequestHomePage {

    //Header
    private final String header = "Shop Locally";
    private final By headerObject = By.xpath("//h1");

    //Textfields
    private final By partCodesTextField = By.xpath("//input[@id='partcodes']");
    private final By partCodeDescriptionTextField = By.xpath("//input[@id='description']");
    private final By manufacturerTextField = By.xpath("//input[@id='manufacturer']");
    private final By categoryTextField = By.xpath("//input[@id='category']");

    //Checkboxes
    private final By includeEPDREquipmentCheckbox = By.xpath("//input[@id='includeEPDR']");
    private final By includeAssembledEquipmentCheckbox = By.xpath("//input[@id='includeAssembled']");

    //Buttons
    private final By searchButton = By.xpath("//div[3]/button");

    //Links
    private final By advancedLink = By.xpath("//a[contains(text(),'Advanced')]");

    //Columns
    private final By partCodeColumn = By.xpath("//th/label");
    private final By descriptionColumn = By.xpath("//th[2]/label");
    private final By manufacturerColumn = By.xpath("//th[3]/label");
    private final By categoryColumn = By.xpath("//th[4]/label");
    private final By typeColumn = By.xpath("//th[5]/label");
    private final By poCodeColumn = By.xpath("//th[6]/label");
    private final By availableColumn = By.xpath("//th[7]/label");
    private final By requestedColumn = By.xpath("//th[8]/label");

    public MaterialsRequestShopNationwidePage (WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyTitle() {
        driver.assertText(headerObject, header);
    }

    public void verifySearchColumns() {
        driver.assertText(partCodesTextField, "Part Code(s)");
    }

    public void verifySearchColumnsAdvanced() {
        driver.verifyTextExists("Part Description");
        driver.verifyTextExists("Manufacturer");
        driver.verifyTextExists("Category");
    }

    public void verifyPage() {
        verifyTitle();
        verifySearchColumns();
    }

    public void verifyResultColumns() {
        driver.assertText(partCodeColumn, "Part Code");
        driver.assertText(descriptionColumn, "Description");
        driver.assertText(manufacturerColumn, "Manufacturer");
        driver.assertText(categoryColumn, "Category");
        driver.assertText(typeColumn, "Type");
        driver.assertText(poCodeColumn, "PO Cost");
        driver.assertText(availableColumn, "Available");
        driver.assertText(requestedColumn, "Requested");
    }

    //Navigate
    public void navigate() {
        selectShopNationwide();
        verifyPage();
    }

    //Input
    public void enterPartCode(String partCode) {
        driver.sendText(partCodesTextField, partCode);
    }

    public void enterPartDescription(String desc) {
        driver.sendText(partCodeDescriptionTextField, desc);
    }

    public void enterManufacturer(String manufacturer) {
        driver.sendText(manufacturerTextField, manufacturer);
    }

    public void enterCategory(String category) {
        driver.sendText(categoryTextField, category);
    }

    //Click

    public void clickIncludeEPDREquipment() {
        driver.click(includeEPDREquipmentCheckbox);
    }

    public void clickIncludeAssembledEquipment() {
        driver.click(includeAssembledEquipmentCheckbox);
    }

    public void clickAdvancedLink() {
        driver.click(advancedLink);
    }

    public void clickSearch() {
        driver.click(searchButton);
    }

    public boolean addItem(String partCode, int qty) {
        verifyResultColumns();
        List<WebElement> availableItems = driver.wd.findElements(By.xpath("//body[@id='ng-app']/div[3]/div[2]/div[6]/div/div/form/div[6]/table/tbody/tr/td/div"));
        int index = driver.findTableValueIndex(availableItems, partCode, 0, 6);

        if (index == 0) {
            driver.sendText(By.xpath("//input[@type='number']"), String.valueOf(qty));
            driver.click(By.xpath("(//button[@type='button'])[2]"));
        } else if (index >= 1) {
            driver.sendText(By.xpath("(//input[@type='number'])[" + index + "]"), String.valueOf(qty));
            driver.click(By.xpath("(//button[@type='button'])[" + (index + 1) + "]"));
        } else {
            System.out.println("Part Code not found");
            return false;
        }
        return true;
    }

    public boolean removeItem(String partCode) {
        verifyResultColumns();
        List<WebElement> availableItems = driver.wd.findElements(By.xpath("//body[@id='ng-app']/div[3]/div[2]/div[6]/div/div/form/div[6]/table/tbody/tr/td/div"));
        int index = driver.findTableValueIndex(availableItems, partCode, 0, 6);

        if (index == 0) {
            driver.click(By.xpath("(//button[@type='button'])[2]"));
        } else if (index >= 1) {
            driver.click(By.xpath("(//button[@type='button'])[" + (index + 1) + "]"));
        } else {
            System.out.println("Part Code not found");
            return false;
        }
        return true;
    }

}
