package net.fulcrum.Pages.Web.Test;

import java.net.MalformedURLException;
import java.util.*;

import net.fulcrum.Pages.Web.LoginPage;
import net.fulcrum.Pages.Web.MaterialsRequest.*;
import net.fulcrum.Routines.Android.XO.AssetToRepair;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class MaterialsRequestTest extends MainTest {

    //private final String title = "CATS Materials Request";
    //private final String application = "catsmr";

    private final String title = "CATS Excess Management";
    private final String application = "catsem";

    private final String approverUser = "CATSADM2";
    private final String approverPW = "catscats11";
    private final String unsubscribedUser = "CATS2 ADMIN";

    private String needByDay;
    private Random rand;
    private int num;
    private Calendar calendar;
    private String partCode = "004001";
    private String partCode2 = "002-0297-14";
    private String partCode3 = "004015";
    private String shipLocation = "SEATTLE (SEA) WAREHOUSE";
    private String shipLocationBU = "NTNWR";
    private String shipLocationAddress = "3440 6TH AVE S SEATTLE,WA";
    private String manufacturer = "DITECH NETWORKS INC";
    private String shipMethod = "UPS";
    private String siteContact = "CATSADM CATSADM";
    private String orderCode;
    private Date date;
    private int qty = 1;
    private int qty2 = 1;
    private int qty3 = 1;

    private MaterialsRequestAllOrdersPage allOrders;
    private MaterialsRequestCartPage cart;
    private MaterialsRequestDashboardPage dashboard;
    private MaterialsRequestEmailResubscribeHistoryPage emailResubscribeHistory;
    private MaterialsRequestEmailResubscribePage emailResubscribe;
    private MaterialsRequestEmailUnsubscribePage emailUnsubscribe;
    private MaterialsRequestEMServiceSettingsPage emServiceSettings;
    private MaterialsRequestMyOrdersPage myOrders;
    private MaterialsRequestOrderFormRejectionPage orderFormRejection;
    private MaterialsRequestOrderPriorityHistoryPage orderPriorityHistory;
    private MaterialsRequestOrderTemplatePage orderTemplate;
    private MaterialsRequestRoleAssignmentPage roleAssignment;
    private MaterialsRequestShipToLocationPage shipToLocation;
    private MaterialsRequestSurveyDashboardPage surveyDashboard;
    private MaterialsRequestSurveySetupPage surveySetup;
    private MaterialsRequestViewSurveyPage viewSurvey;
    private MaterialsRequestShopLocallyPage shopLocally;
    private MaterialsRequestShopNationwidePage shopNationwide;

    @BeforeMethod(alwaysRun = true)
    public void StageData() {
        allOrders = new MaterialsRequestAllOrdersPage(driver, url);
        cart = new MaterialsRequestCartPage(driver, url);
        dashboard  = new MaterialsRequestDashboardPage(driver, url);
        emailResubscribeHistory = new MaterialsRequestEmailResubscribeHistoryPage(driver, url);
        emailResubscribe = new MaterialsRequestEmailResubscribePage(driver, url);
        emailUnsubscribe = new MaterialsRequestEmailUnsubscribePage(driver, url);
        emServiceSettings = new MaterialsRequestEMServiceSettingsPage(driver, url);
        myOrders = new MaterialsRequestMyOrdersPage(driver, url);
        orderFormRejection = new MaterialsRequestOrderFormRejectionPage(driver,url);
        orderPriorityHistory = new MaterialsRequestOrderPriorityHistoryPage(driver, url);
        orderTemplate = new MaterialsRequestOrderTemplatePage(driver, url);
        roleAssignment = new MaterialsRequestRoleAssignmentPage(driver, url);
        shipToLocation = new MaterialsRequestShipToLocationPage(driver, url);
        surveyDashboard = new MaterialsRequestSurveyDashboardPage(driver, url);
        surveySetup = new MaterialsRequestSurveySetupPage(driver, url);
        viewSurvey = new MaterialsRequestViewSurveyPage(driver, url);
        shopLocally = new MaterialsRequestShopLocallyPage(driver, url);
        shopNationwide = new MaterialsRequestShopNationwidePage(driver, url);
        rand = new Random();
        num = rand.nextInt(10000) + 1;
        calendar = Calendar.getInstance();
        needByDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + 3);
        applicationURL = url + application;
    }

    @TestRailCase(selfReporting = true, automationId="1000")
    @Test(priority = 1)
    public void validateLogin() throws InterruptedException {
        driver.wd.get(applicationURL);
        Assert.assertEquals(title, driver.getTitle());
    }

    @TestRailCase(selfReporting = true, automationId="1000")
    @Test(priority = 2)
    public void login() throws InterruptedException {
        login.login(title, userName, passWord);
    }
/*
    @Test(priority = 3, enabled = false)
    public void validatePage() {
        dashboard.validatePage();
    }

    @Test(priority = 4, enabled = true)
    public void unsubcribeUser() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.verifyPrompt();
        emailUnsubscribe.clickYes();
    }

    @Test(priority = 5, enabled = false)
    public void viewMyOrders() {
        dashboard.clickDashboard();
        dashboard.clickViewMyOrders();
    }

    @Test(priority = 6, enabled = false)
    public void clickShowAllOrders() {
        myOrders.clickShowAllOrders();
    }

    @Test(priority = 7, enabled = false)
    public void navigateBackToMyOrders() {
        allOrders.clickShowMyOrders();
    }

    @Test(priority = 8, enabled = false)
    public void viewOrdersToApprove() {
        dashboard.refresh(applicationURL);
        dashboard.clickOrdersToAppove();
    }

    @Test(priority = 9, enabled = false)
    public void viewCart() {
        dashboard.refresh(applicationURL);
        dashboard.clickCart();
    }

    @Test(priority = 10, enabled = false)
    public void validateEmptyCart() {
        cart.verifyEmptyCart();
    }

    @Test(priority = 11, enabled = true)
    public void verifyUnsubcribe() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.verifyUnsubcribedMessage();
    }

    @Test(priority = 12, enabled = true)
    public void verifyUnsubcribeColumns() {
        emailUnsubscribe.verifyColumns();
    }

    @Test(priority = 13, enabled = true)
    public void resubscribeUser() {
        emailResubscribe.navigate();
        emailResubscribe.clickCheckbox(1);
        emailResubscribe.clickResubscribe();
        emailResubscribe.verifyUnsubscribePrompt();
        emailResubscribe.clickYes();
        emailResubscribe.clickEmailUnsubscribe();
        emailUnsubscribe.verifyPrompt();
    }


    @Test(priority = 14, enabled = true)
    public void createLocationType() {
        String locationTypeOption = "NJ REFURB NF VENDOR STAGING";
        shipToLocation.navigate();
        shipToLocation.selectLocationType(locationTypeOption);
        shipToLocation.clickSave();
    }

    @Test(priority = 15, enabled = true)
    public void deleteLocationType() {
        String locationType = "REFURB NF VENDOR STAGING";
        shipToLocation.navigate();
        Assert.assertEquals(shipToLocation.deleteLocationType(locationType), true);
        shipToLocation.clickDelete();
        shipToLocation.clickYes();
    }

    @Test(priority = 16, enabled = true)
    public void logout() {
        menu.legacyLogout();
    }

    @Test(priority = 17, enabled = true)
    public void loginApprover() throws InterruptedException, StaleElementReferenceException {

        //New approver login page to prevent stale element reference
        try {
            login.login(title, approverUser, approverPW);
        } catch(StaleElementReferenceException e) {
            LoginPage loginApprover = new LoginPage(driver, url);
            loginApprover.login(title, approverUser, approverPW);
        }

    }

    @Test(priority = 18, enabled = true)
    public void verifyUnsubscribePrompt() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.verifyPrompt();
    }

    @Test(priority = 19, enabled = true)
    public void unsubscribeApprover() {
        emailUnsubscribe.clickYes();
    }

    @Test(priority = 20, enabled = true)
    public void verifyUnsubcribeApprover() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.verifyUnsubcribedMessage();
    }

    @Test(priority = 21, enabled = true)
    public void verifyUnsubcribeColumnsApprover() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.verifyColumns();
    }
*/
/*
    @Test(priority = 22, enabled = true)
    public void verifyShopNationwidePage() {
        shopNationwide.navigate();
    }

    @Test(priority = 23, enabled = true)
    public void verifyAdvancedColumns() {
        shopNationwide.clickAdvancedLink();
        shopNationwide.verifySearchColumnsAdvanced();
    }

    @Test(priority = 25, enabled = true)
    public void addItemsToCartMR() {
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode, qty);
    }

    @Test(priority = 26, enabled = true)
    public void addItemsToCartMR2() {
        shopNationwide.navigate();
        shopNationwide.enterManufacturer(manufacturer);
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode2, qty2);
    }

    @Test(priority = 27, enabled = true)
    public void addItemsToCartMR3() {
        shopNationwide.navigate();
        shopNationwide.enterManufacturer("");
        shopNationwide.enterPartCode(partCode3);
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode3, qty3);
    }
    @Test(priority = 28, enabled = true)
    public void updateQtyItem() {
        cart.navigate();
        cart.updateQty(partCode, 2);
    }

    @Test(priority = 29, enabled = true)
    public void removeItemFromCartMR() {
        shopNationwide.navigate();
        shopNationwide.removeItem(partCode);
    }

    @Test(priority = 30, enabled = true)
    public void submitMR() {
        cart.navigate();
        cart.enterRequiredData(shipMethod, shipLocation, "24-November-2016", shipLocationBU, siteContact);
        cart.enterNotes("MR Created by Test Automation");
        verifyText(partCode);
        verifyText(partCode3);
        verifyText(qty3);
        verifyText(qty);
        cart.clickSubmitButton();
    }

    @Test(priority = 31, enabled = false)
    public void verifyMRCreated() {
    }

    @Test(priority = 32, enabled = true)
    public void cancelMRTopButton() {
        shopNationwide.navigate();
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode, 1);
        cart.navigate();
        cart.verifyHeaders();
        cart.clickTopCancelButton();
        dashboard.verifyHeader();
    }

    @Test(priority = 33, enabled = false)
    public void cancelMRBottomButton() {
        shopNationwide.navigate();
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode3, 1);
        cart.navigate();
        cart.verifyHeaders();
        cart.clickBottomCancelButton();
        dashboard.verifyHeader();
    }

    @Test(priority = 33, enabled = true)
    public void addInvalidQtyMR() {
        shopNationwide.navigate();
        shopNationwide.clickSearch();
        shopNationwide.addItem(partCode3, 1);
        cart.navigate();
        cart.enterRequiredData(shipMethod, shipLocation, "24-November-2016", shipLocationBU, siteContact);
        cart.enterNotes("MR Created by Test Automation");
        cart.updateQty(partCode3, 9999);
        //cart.clickSubmitButton();
        driver.click(By.xpath("//a[2]/button"));
        cart.verifyInvalidQtyMessage();
    }

   @Test(priority = 56, enabled = false)
    public void searchOrder() {
        orderCode = "EM003144";
        myOrders.navigate();
        myOrders.enterOrderCode(orderCode);
        myOrders.clickSearch();
    }
*/

    //Admin - Admin Page Testcases
   @Test(priority = 56, enabled = true)
    public void adminPage() {
        dashboard.validateAdmin();
    }

    @Test(priority = 57, enabled = true)
    public void verifyAdminPanel() {
        dashboard.clickAdmin();
        dashboard.selectEMModuleSettings();
    }

    @Test(priority = 58, enabled = true)
    public void verifyActiveSecurityProfiles() {
        roleAssignment.navigate();
        roleAssignment.verifyActiveSecurityProfiles();
    }

    @Test(priority = 59, enabled = true)
    public void verifyRoles() {
        roleAssignment.navigate();
        roleAssignment.verifyRoles();
    }

    @Test(priority = 60, enabled = true)
    public void verifyRoleAssignmentColumns() {
        roleAssignment.verifyColumns();
    }

    @Test(priority = 61, enabled = true)
    public void verifyDefaultRoles() {
        roleAssignment.navigate();
        Assert.assertEquals(roleAssignment.verifyDefaultRoles(), true);
    }

    @Test(priority = 62, enabled = true)
    public void oneProfileToOneRole() {
        roleAssignment.navigate();
        List<String> active = roleAssignment.getActiveSecurityProfiles();
        for (int i = 0; i < active.size(); i++) {
            Assert.assertEquals(roleAssignment.verifyDropdownProfile(active.get(i)), false);
        }
    }

    @Test(priority = 63, enabled = true)
    public void verifyUnassignedProfiles() {
        roleAssignment.navigate();
        roleAssignment.verifyInactiveProfiles();
    }

    @Test(priority = 64, enabled = true)
    public void verifyAddingProfile() {
        roleAssignment.navigate();
        roleAssignment.addProfileRole("REPORTS USER", "REVIEWER");
    }

    @Test(priority = 65, enabled = true)
    public void verifySaveButton() {
        roleAssignment.navigate();
        Assert.assertEquals(driver.verifyIfObjectIsEnabled(roleAssignment.getSaveButton()), false);
    }

    @Test(priority = 66, enabled = true)
    public void verifyDeleteButton() {
        roleAssignment.navigate();
        Assert.assertEquals(driver.verifyIfObjectIsDisplayed(roleAssignment.getDeleteButton()), false);
    }


    @Test(priority = 67, enabled = true)
    public void deleteProfileEntry() {
        roleAssignment.navigate();
        Assert.assertEquals(roleAssignment.deleteProfileEntry("REPORTS USER"), true);
    }

    @Test(priority = 68, enabled = true)
    public void verifyEmailResubscribeTab() {
        emailResubscribe.navigate();
    }

    @Test(priority = 69, enabled = true)
    public void verifyProfileRelisted() {
        roleAssignment.navigate();
        Assert.assertEquals(roleAssignment.verifyDropdownProfile("REPORTS USER"), false);
    }

    @Test(priority = 70, enabled = true)
    public void verifyResubscribeButtonNotDisplayed() {
        emailResubscribe.navigate();
        Assert.assertEquals(driver.verifyIfObjectIsDisplayed(emailResubscribe.getResubcribeButton()), false);
    }

    @Test(priority = 71, enabled = true)
    public void verifyUnsubscribeY() {
        emailResubscribe.navigate();
        Assert.assertEquals(emailResubscribe.verifyUnsubscribeY(), true);
    }

    @Test(priority = 72, enabled = true)
    public void verifyFields() {
        emailResubscribe.navigate();
        emailResubscribe.verifyColumns();
    }

    @Test(priority = 73, enabled = true)
    public void unsubscribeAdminUser() {
        emailUnsubscribe.navigate();
        emailUnsubscribe.clickYes();
    }

    @Test(priority = 74, enabled = false)
    public void searchEmailResubscribe() {
        emailResubscribe.navigate();
        emailResubscribe.enterSearchFilter(unsubscribedUser);
        emailResubscribe.verifyFirstUser(unsubscribedUser);
    }

    @Test(priority = 75, enabled = true)
    public void resubscribeUser() {
        emailResubscribe.navigate();
        Assert.assertEquals(emailResubscribe.resubscribeUser("CATSADM CATSADM"), true);
    }

    @Test(priority = 80, enabled = true)
    public void createLocationType() {
        String locationTypeOption = "NJ REFURB NF VENDOR STAGING";
        shipToLocation.navigate();
        shipToLocation.selectLocationType(locationTypeOption);
        shipToLocation.clickSave();
    }

    @Test(priority = 81, enabled = true)
    public void deleteLocationType() {
        String locationType = "REFURB NF VENDOR STAGING";
        shipToLocation.navigate();
        Assert.assertEquals(shipToLocation.deleteLocationType(locationType), true);
        shipToLocation.clickDelete();
        shipToLocation.clickYes();
    }

    @Test(priority = 82, enabled = true)
    public void verifyCannotDeleteAll() {
        String locationType = "NETWORK - VZW STAGING";
        String codeComponent = "NS";
        Assert.assertEquals(shipToLocation.deleteLocationType("NETWORK - VZW STAGING"), true);
        shipToLocation.clickDelete();
        shipToLocation.clickYes();
        driver.verifyIfObjectIsEnabled(shipToLocation.getDeleteButton());
        shipToLocation.selectLocationType(codeComponent + " " + locationType);
        shipToLocation.clickSave();
    }

    @Test(priority = 83, enabled = true)
    public void verifyInactiveLocationTypes() {
        shipToLocation.navigate();
        shipToLocation.verifyInactiveLocationTypes();
    }

    @Test(priority = 90, enabled = true)
    public void verifyEmailResubscribeHistory() {
        emailResubscribeHistory.navigate();
        emailResubscribeHistory.verifyColumns();
    }

    @Test(priority = 95, enabled = true)
    public void verifyOrderPriorityHistory() {
        orderPriorityHistory.navigate();
        orderPriorityHistory.verifyColumns();
    }

    @Test(priority = 100, enabled = false)
    public void deleteAdmin() {
        Assert.assertEquals(roleAssignment.deleteProfileEntry("ADMIN"), true);
        roleAssignment.verifyAdminRemoveMessage();
    }

    @Test(priority = 1000, enabled = true)
    public void logoutApprover() {
        menu.legacyLogout();
    }
}
