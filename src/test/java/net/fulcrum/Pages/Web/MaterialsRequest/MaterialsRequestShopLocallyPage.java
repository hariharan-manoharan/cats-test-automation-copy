package net.fulcrum.Pages.Web.MaterialsRequest;
import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;

public class MaterialsRequestShopLocallyPage extends  MaterialsRequestHomePage {

    //Header
    private final String header = "Shop Locally";
    private final By headerObject = By.xpath("//h1");

    //Dropdowns
    private final By fromBUDropdown = By.xpath("//select[@id='fromWarehouseMultiple']");
    private final By fromStatusDropDown = By.xpath("//select[@id='fromStatus']");

    //Textfields
    private final By fromWarehouseTextField = By.xpath("//input[@id='fromWarehouse.name']");
    private final By projectTextfield = By.xpath("//input[@id='project']");
    private final By projectDescriptionTextfield = By.xpath("//input[@id='projectDescription']");
    private final By partCodeTextField = By.xpath("//input[@id='partCode']");
    private final By containerCodeTextField = By.xpath("//input[@id='containerCode']");
    private final By poCodeTextField = By.xpath("//input[@id='poCode']");
    private final By reuseCommentTextField = By.xpath("//input[@id='reuseComment']");
    private final By reuseDeploymentDateTextField = By.xpath("//input[@id='reuseDeployDate']");
    private final By reuseProjectLocationTextField = By.xpath("//input[@id='reuseProject']");

    //Checkboxes
    private final By includeEPDREquipmentCheckbox = By.xpath("//input[@id='includeEPDR']");
    private final By includeAssembledEquipment = By.xpath("//input[@id='includeAssembled']");

    //Buttons
    private final By searchButton = By.xpath("//div[3]/div/button");
    private final By firstButton = By.xpath("//a[contains(text(),'First')]");
    private final By previousButton = By.xpath("//a[contains(text(),'Previous')]");
    private final By nextButton = By.xpath("//a[contains(text(),'Next')]");
    private final By lastButton = By.xpath("//a[contains(text(),'Last')]");

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

    public MaterialsRequestShopLocallyPage(WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void verifyTitle() {
        driver.assertText(headerObject, header);
    }

    public void verifySearchColumns() {
        driver.assertText(fromWarehouseTextField, "From Warehouse");
        driver.assertText(poCodeTextField, "PO #");
        driver.assertText(partCodeTextField, "Part Code");
    }

    public void verifySearchColumnsAdvanced() {
        driver.assertText(fromBUDropdown, "From BU");
        driver.assertText(fromStatusDropDown, "From Status");
        driver.assertText(containerCodeTextField, "Container Code");
        driver.assertText(projectTextfield, "Project");
        driver.assertText(projectDescriptionTextfield, "Project Description");
        driver.assertText(reuseProjectLocationTextField, "Reuse Project Location");
        driver.assertText(reuseDeploymentDateTextField, "Reuse Deployment Date");
        driver.assertText(reuseCommentTextField, "Reuse Comment");
        driver.assertText(includeAssembledEquipment, "Include EDPR Equipment");
    }

    //Input
    public void enterFromWarehouse(String warehouse) {
        driver.sendText(fromWarehouseTextField, warehouse);
    }

    public void enterPOCode(String poCode) {
        driver.sendText(poCodeTextField, poCode);
    }

    public void enterPartCode(String partCode) {
        driver.sendText(partCodeTextField, partCode);
    }

    public void enterContainerCode(String containerCode) {
        driver.sendText(containerCodeTextField, containerCode);
    }

    public void enterProject(String projectCode) {
        driver.sendText(projectTextfield, projectCode);
    }

    public void enterProjectDescription(String desc) {
        driver.sendText(projectDescriptionTextfield, desc);
    }

    public void enterReuseProjectLocation(String reuseProjectLocation) {
        driver.sendText(reuseProjectLocationTextField, reuseProjectLocation);
    }

    public void enterReuseDeploymentDate(String date) {
        driver.sendText(reuseDeploymentDateTextField, date);
    }

    public void enterReuseComment(String comment) {
        driver.sendText(reuseCommentTextField, comment);
    }

    //Click
    public void clickIncludeEDPREquipment() {
        driver.click(includeAssembledEquipment);
    }

    public void clickIncludeAssembledEquipment() {
        driver.click(includeAssembledEquipment);
    }

    public void clickAdvancedLink() {
        driver.click(advancedLink);
    }

    public void clickSearchButton() {
        driver.click(searchButton);
    }

    //Select dropdown
    public void selectFromBu(String locationBU) {
        driver.selectDropDownValue(fromBUDropdown, locationBU);
    }

    public void selectFromStatus(String status) {
        driver.selectDropDownValue(fromStatusDropDown, status);
    }
 }
