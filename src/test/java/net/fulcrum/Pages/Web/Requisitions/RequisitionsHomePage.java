package net.fulcrum.Pages.Web.Requisitions;

import junit.framework.Assert;
import net.fulcrum.Drivers.WebBrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;


public class RequisitionsHomePage {

    private WebBrowserDriver driver;
    private By element;
    private String url;

    private final String application = "requisitions";

    //Tab Objects
    private By approvedRequisitionsTab = By.linkText("Approved Requisitions");
    private By myRequisitionsTab = By.linkText("My Requisitions");
    private By allRequisitionsTab = By.linkText("All Requisitions");
    private By assignedToMeTab = By.linkText("Assigned To Me");
    private By assignedToAlternatesTab = By.linkText("Assigned To Alternates");
    private By requisitionsToProcessTab = By.linkText("Requisitions To Process");

    //Button Objects
    private By createButton = By.id("createButton");
    private By nextButton = By.xpath("//li[@id='allRequisitions_next']/a");
    private By previousButton = By.xpath("//li[@id='allRequisitions_previous']/a");
    private By recreateButton = By.id("recreateRequisition");
    private By confirmRecreateButton = By.id("confirmRecreateButton");
    private By requisitionsToProcessSearchButton = By.xpath("//div[@id='approvableRequisitions_filter']/label/div");
    private By allRequisitionsSearchButton = By.xpath("//div[@id='allRequisitions_filter']/label/div");
    private By approvedRequisitionsSearchButton = By.xpath("//div[@id='approvedRequisitions_filter']/label/div");
    private By myRequisitionsSearchButton = By.xpath("//div[@id='myRequisitions_filter']/label/div");
    private By assignedToMeSearchButton = By.xpath("//div[@id='directlyApprovableRequisitions_filter']/label/div");
    private By assignedToAlternateSearchButton = By.xpath("//div[@id='alternateApprovableRequisitions_filter']/label/div");
    private By approveButton = By.id("approve");

    //Dropdown Objects
    private By allRequisitionsShowEntriesDropDown = By.name("allRequisitions_length");
    private By myRequisitionsShowEntriesDropDown = By.name("myRequisitions_length");
    private By approvedRequisitionsShowEntriesDropDown = By.name("approvedRequisitions_length");
    private By requisitionsToProcessShowEntriesDropDown = By.name("approvableRequisitions_length");

    //Search Box Objects
    private By allRequisitionsSearchBox = By.xpath("//div[@id='allRequisitions_filter']/label/input");
    private By approvedRequisitionsSearchBox = By.xpath("//div[@id='approvedRequisitions_filter']/label/input");
    private By myRequisitionsSearchBox = By.xpath("//div[@id='myRequisitions_filter']/label/input");
    private By assignedToMeSearchBox = By.xpath("//div[@id='directlyApprovableRequisitions_filter']/label/input");
    private By assignedToAlternateSearchBox = By.xpath("//div[@id='alternateApprovableRequisitions_filter']/label/input");
    private By requisitionsToProcessSearchBox = By.xpath("//div[@id='approvableRequisitions_filter']/label/input");

    //Link Objects
    private By reviewYourOrderLink = By.linkText("Review your order");
    private By reviewRejectedOrderLink = By.linkText("Review the rejected order");

    //Menu Option Objects
    private By requisitionTypesOption = By.xpath("//a[contains(@href, '/requisitions/admin/requisition-types')]");
    private By alternateApproversOption = By.linkText("Alternate Approvers");
    private By alternateApproverOption = By.linkText("Alternate Approver");
    private By manageKitsOption = By.xpath("//a[contains(@href, '/requisitions/boms')]");

    private By firstRequisition = By.xpath("//td/a");

    public RequisitionsHomePage (WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    //Validation
    public void validateTitle() {
        String getTitle = driver.wd.getTitle();
        Assert.assertEquals("CATS Requisitions:", getTitle);
    }

    public void refreshPage() {
        driver.wd.get(url + application);
    }

    public void validatePage () {
        validateTitle();
    }


    //Navigate Tabs
    public void clickApprovedRequisitionsTab() {
        driver.click(approvedRequisitionsTab);
    }

    public void clickMyRequisitionsTab() {
        driver.click(myRequisitionsTab);
    }

    public void clickAllRequisitionsTab() {
        driver.click(allRequisitionsTab);
    }

    public void clickRequisitionsToProcessTab() {
        driver.click(requisitionsToProcessTab);
    }

    public void clickAssignedToMeTab() {
        driver.click(assignedToMeTab);
    }

    public void clickAssignedToAlternatesTab() {
        driver.click(assignedToAlternatesTab);
    }

    //Searches
    public void enterAllRequisitionsSearch(String search) {
        clickAllRequisitionsTab();
        driver.sendText(allRequisitionsSearchBox, search);
    }

    public void enterMyRequisitionsSearch(String search) {
        clickMyRequisitionsTab();
        driver.sendText(myRequisitionsSearchBox, search);
    }

    public void enterApprovedRequisitionsSearch(String search) {
        clickApprovedRequisitionsTab();
        driver.sendText(approvedRequisitionsSearchBox, search);
    }

    public void enterRequisitionsToProcessSearch(String search) {
        clickRequisitionsToProcessTab();
        driver.sendText(requisitionsToProcessSearchBox, search);
    }

    public void enterAssignedToMeSearch(String search) {
        clickAssignedToMeTab();
        driver.sendText(assignedToMeSearchBox, search);
    }


    public void enterAssignedToAlternateSearch(String search) {
        clickAssignedToAlternatesTab();
        driver.sendText(assignedToAlternateSearchBox, search);
    }

    //Menu Options
    public void selectRequisitionTypesOption() {
        driver.click(requisitionTypesOption);
    }

    public void selectAlternateApproversOption(){
        driver.click(alternateApproversOption);
    }

    public void selectAlternateApproverOption(){
        driver.click(alternateApproverOption);
    }

    public void selectRequisitionType (String type) {
        driver.click(By.linkText(type));
    }

    public void selectManageKitsOption() {
        driver.click(manageKitsOption);
    }

    //Buttons
    public void clickAllRequisitionsSearchButton(){
        driver.click(allRequisitionsSearchButton);
    }

    public void clickMyRequisitionsSearchButton(){
        driver.click(myRequisitionsSearchButton);
    }

    public void clickRequisitionsToProcessSearchButton(){
        driver.click(requisitionsToProcessSearchButton);
    }

    public void clickApprovedRequisitionsSearchButton(){
        driver.click(approvedRequisitionsSearchButton);
    }

    public void clickPreviousButton(){
        driver.click(previousButton);
    }

    public void clickNextButton(){
        driver.click(nextButton);
    }

    public void clickCreateButton() {
        driver.click(createButton);
    }

    public void clickRecreateButton() {
        driver.click(recreateButton);
    }

    public void clickConfirmRecreateButton() {
        driver.click(confirmRecreateButton);
    }

    public void clickFirstRequisitionToApprove() {
        driver.click(firstRequisition);
    }

    public void clickApproveButton() {
        driver.click(approveButton);
    }

    public void clickAssignedToMeSearchButton() {
        driver.click(assignedToMeSearchButton);
    }

    public void clickAssignedToAlternateSearchButton() {
        driver.click(assignedToAlternateSearchButton);
    }

    //Links
    public void clickReviewYourOrder() {
        driver.click(reviewYourOrderLink);
    }

    public void clickReviewRejectedOrder() {
        driver.click(reviewRejectedOrderLink);
    }


}
