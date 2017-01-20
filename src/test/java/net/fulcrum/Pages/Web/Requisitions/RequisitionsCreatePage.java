package net.fulcrum.Pages.Web.Requisitions;

import junit.framework.Assert;
import net.fulcrum.Drivers.WebBrowserDriver;

import org.openqa.selenium.By;

public class RequisitionsCreatePage {

    private final WebBrowserDriver driver;
    
    private final By businessUnitDropDown = By.id("businessUnitId");
    private final By projectDropDown = By.id("projectId");
    private final By shipToLocationDropDown = By.id("shipToLocationId");
    private final By rushReason = By.id("rushReason");
    private final By needByDatePicker = By.id("needByDate");
    private final By onBehalfOffTextField = By.id("onBehalfOfContactCode");
    private final By configurationLocationDropDown = By.id("configurationLocationId");
    private final By notesTextField = By.id("notes");
    private final By jobTextField = By.id("job");
    private final By jobSearchButton = By.xpath("//a[@id='jobSearchIcon']/i");
    private final By addLocationLink = By.linkText("add one");
    private final By addSomeLink = By.linkText("add some");
    private final By kitsTab = By.linkText("Kits");
    private final By searchTab = By.id("searchTabButton");
    private final By partSearchTextField = By.id("partSearchText");
    private final By partSearchButton = By.id("partSearchButton");
    private final By partSearchCloseButton = By.xpath("//button[2]");
    private final By nextButton = By.id("nextButton");
    private final By previousButton = By.id("previousButton");
    private final By cancelButton = By.id("cancelRequisition");
    private final By editButton = By.id("editRequisition");
    private final By addLocationButton = By.linkText("Add Location");
    private final By addItemsButton = By.id("addItemsButton");
    private final By createKitButton = By.linkText("Create Kit");
    private final By applyItemsButton = By.xpath("//button[2]");
    private final By submitForApprovalButton = By.linkText("Submit for Approval");
    private final By defaultRadioButton = By.name("shipVia");
    private final By alternateRadioButton = By.xpath("(//input[@name='shipVia'])[2]");
    private final By countryDropDown = By.name("altCountry");
    private final By address1TextField = By.name("altAddress1");
    private final By address2TextField = By.name("altAddress2");
    private final By address3TextField = By.name("altAddress3");
    private final By cityTextField = By.name("altCity");
    private final By stateProvinceTextField = By.name("altStateProvince");
    private final By postalCodeField = By.name("altPostalcode");
    private final By countryField = By.name("altCountry");
    private final By addLocationSearchButton =  By.xpath("//form[@id='addLocationSearchForm']/div/span/button");
    private final By quantityPartLine1 = By.xpath("//input[@value='0']");
    private final By taskCodePartLine4 = By.xpath("//tr[4]/td[3]/select");
    private final By taskCodePartLine1 = By.xpath("//form/div[2]/div/div/div[2]/div/div/table/tbody/tr/td[3]/select");
    private final By quantityPartLine4 = By.xpath("(//input[@value='0'])[4]");
    private final By confirmSubmitButton = By.id("confirmSubmitButton");
    private final By firstRequisition = By.xpath("//td/a");

    private final String header = "Create Requisition";
    private final By headerObject = By.xpath("//h3");

    public RequisitionsCreatePage(WebBrowserDriver driver) {
        this.driver = driver;
    }

    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions:", getTitle);
    }

    public void validatePage() {
        validateTitle();
    }

    public void selectBusinessUnitDropDown(String businessUnit) {
        driver.selectDropDownValue(businessUnitDropDown, businessUnit);
    }

    public void selectProjectDropDown(String project){
        driver.selectDropDownValue(projectDropDown, project);
    }

    public void selectShipToDropDown(String location){
        driver.selectDropDownValue(shipToLocationDropDown, location);
    }

    public void selectRushReasonDropDown(String reason){
        driver.selectDropDownValue(rushReason, reason);
    }

    public void clickNeedByDate() {
        driver.waitForObject(needByDatePicker, 30);
        driver.click(needByDatePicker);
        driver.click(needByDatePicker);
    }

    public void enterOnBehalfOf(String contact) {
        driver.sendText(onBehalfOffTextField, contact);
    }

    public void clickConfigurationLocationDropdown(){
        driver.click(configurationLocationDropDown);
    }

    public void enterNotes(String notes){
        driver.sendText(notesTextField, notes);
    }

    public void enterJob(String job){
        driver.click(jobSearchButton);
        driver.click(By.linkText(job));
    }
    
    public void clickAddLocation() {
        driver.click(addLocationLink);
    }

    public void clickSomeItems(){
        driver.click(addSomeLink);
    }

    public void clickKitsTab(){
        driver.click(kitsTab);
    }
    
    public void clickSearchTab(){
        driver.click(searchTab);
    }

    public void enterPart(String part){
        driver.sendText(partSearchTextField, part);
    }
    
    public void clickPartSearchButton(){
        driver.click(partSearchButton);
    }

    public void clickPartSearchCloseButton(){
        driver.click(partSearchCloseButton);
    }

    public void clickNext(){
        driver.click(nextButton);
    }
    
    public void clickPrevious() {
        driver.click(previousButton);
    }


    public void clickCancelButton() {
        driver.click(cancelButton);
    }

    public void clickEditButton() {
        driver.click(editButton);
    }

    public void clickAddItems() {
        driver.click(addItemsButton);
    }

    public void clickCreateKitButton(){
        driver.click(createKitButton);
    }

    public void clickSubmitForApprovalButton() {
        driver.click(By.linkText("Submit for Approval"), 30);
    }

    public void clickDefaultRadioButton(){
        driver.click(defaultRadioButton);
    }

    public void clickAlternateRadioButton() {
        driver.click(alternateRadioButton);
    }

    public void clickCountryDropDown(){
        driver.click(countryDropDown);
    }

    public void enterAddress1Field(String text) {
        driver.sendText(address1TextField, text);
    }

    public void enterAddress2Field(String text) {
        driver.sendText(address2TextField, text);
    }

    public void enterAddress3Field(String text) {
        driver.sendText(address3TextField, text);
    }

    public void enterCityTextField(String city) {
        driver.sendText(cityTextField, city);
    }

    public void enterStateProvinceField(String stateProvince){
        driver.sendText(stateProvinceTextField, stateProvince);
    }

    public void enterPostalcodeField(String postalCode){
        driver.sendText(postalCodeField, postalCode);
    }

    public void enterCountryField(String country){
        driver.sendText(countryField, country);
    }

    public void clickAddLocationButton() {
        driver.click(addLocationButton);
    }

    public void selectDate(String day) {
        driver.click(By.linkText(day));
    }

    public void enterDate(String day) {
        driver.sendText(needByDatePicker, day);
    }

    public void selectLocation(String location) {
        driver.click(By.linkText(location));
    }

    public void clickAddLocationSearchButton() {
        driver.click(addLocationSearchButton);
    }

    public void enterQtyPartLine1(int qty) {
        driver.sendText(quantityPartLine1, String.valueOf(qty));
    }

    public void selectTaskPartLine1(String task) {
        driver.selectDropDownValue(taskCodePartLine1, task);
    }

    public void enterQtyPartLine4(int qty) {
        driver.sendText(quantityPartLine4, String.valueOf(qty));
    }

    public void selectTaskPartLine4(String task) {
        driver.selectDropDownValue(taskCodePartLine4, task);
    }

    public void clickComfirmSubmitButton() {
        driver.click(confirmSubmitButton);
    }

    public void create(String day, String locationBU, String project, String job, String shipToLocation,
                                  String rushReason, String installLocation, String taskCode, int quantity) {
        driver.assertText(headerObject, header);
        selectBusinessUnitDropDown(locationBU);
        selectProjectDropDown(project);
        enterJob(job);
        selectShipToDropDown(shipToLocation);
        clickNeedByDate();
        clickNeedByDate();
        selectDate(day);
        selectRushReasonDropDown(rushReason);
        clickNext();
        clickAddLocationButton();
        clickAddLocationSearchButton();
        selectLocation(installLocation);
        clickAddItems();
        clickPartSearchButton();
        selectTaskPartLine1(taskCode);
        enterQtyPartLine1(quantity);
        clickPartSearchCloseButton();
        clickSubmitForApprovalButton();
        clickComfirmSubmitButton();
        
    }
}

