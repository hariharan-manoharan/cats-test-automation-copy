package net.fulcrum.Pages.Web.Test;

import java.net.MalformedURLException;
import java.util.*;

import net.fulcrum.Pages.Web.Requisitions.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.annotations.*;
import org.testng.Assert;

@Listeners(TestRailListener.class)
public class RequisitionsHomeTest extends MainTest {

    private final String title = "CATS Requisitions:";
    private final String application = "requisitions";

    protected String locationBU = "BOSTON";
    protected String project = "7701LN16010 | DSX-SONET EQUIP-BOSTON 2016";
    protected String job = "TBS R11622664-ETL-D";
    protected String shipToLocation = "BOS-WAREHOUSE | 89 FULKERSON ST";
    protected String rushReason = "Re-Engineered Order";
    protected String installLocation = "ENG-LAB";
    protected String taskCode = "TN204";
    protected int quantity;
    protected String newType;
    protected String needByDay;
    protected Random rand;
    protected int num;
    protected Calendar calendar;

   @BeforeMethod(alwaysRun = true)
   public void StageData() {
      requisitionsHome  = new RequisitionsHomePage(driver, url);
      requisitionsAddType  = new RequisitionsAddTypePage(driver);
      requisitionsTypes = new RequisitionsTypesPage(driver);
      requisitions  = new RequisitionsPage(driver, url);
      reqsCreate = new RequisitionsCreatePage(driver);
      reqsAlternateApprover = new RequisitionsAlternateApproverPage(driver, url);
      reqsKits = new RequisitionsKitsPage(driver, url);
      rand = new Random();
      num = rand.nextInt(10000) + 1;
      calendar = Calendar.getInstance();
      needByDay = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH) + 3);
    }

    @TestRailCase(selfReporting = true, automationId="1000")
    @Test(priority = 1)
    public void login() throws InterruptedException {
        System.out.println("IN LOGIN");
        driver.wd.get(url + application);
        login.login(title, userName, passWord);
    }

    @Test(priority = 2, enabled = true)
    public void validatePage() {
        requisitionsHome.validatePage();
    }

    @TestRailCase(selfReporting = true, automationId="1002")
    @Test(priority = 3, enabled = true)
    public void navigateToApprovedRequisitions() {
        requisitionsHome.clickApprovedRequisitionsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1001")
    @Test(priority = 4, enabled = true)
    public void navigateToMyApprovedRequisitions() {
        requisitionsHome.clickMyRequisitionsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1003")
    @Test(priority = 5, enabled = true)
    public void navigateToAllApprovedRequisitions() {
        requisitionsHome.clickAllRequisitionsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1051")
    @Test(priority = 6, enabled = false)
    public void show10Entries() {
        //requisitionsHome.myRequisitionsSelectEntries(10);
    }

    @TestRailCase(selfReporting = true, automationId="1051")
    @Test(priority = 7, enabled = false)
    public void show50Entries() {
        //requisitionsHome.myRequisitionsSelectEntries(50);
    }

    @TestRailCase(selfReporting = true, automationId="1052")
    @Test(priority = 8, enabled = true)
    public void showNextPage() {
        requisitionsHome.clickAllRequisitionsTab();
        requisitionsHome.clickNextButton();
    }

    @TestRailCase(selfReporting = true, automationId="1053")
    @Test(priority = 9, enabled = true)
    public void showPreviousPage() {
        requisitionsHome.clickPreviousButton();
    }

    @TestRailCase(selfReporting = true, automationId="1050")
    @Test(priority = 10, enabled = true)
    public void clickRequisitionsType() {
        menu.clickSettingsMenu();
        requisitionsHome.selectRequisitionTypesOption();
    }

    @Test(priority = 11, enabled = true)
    public void refresh() {
        requisitionsHome.refreshPage();
    }
/*
    @TestRailCase(selfReporting = true, automationId="1054")
    @Test(priority = 12, enabled = true)
    public void myRequisitionsTestSearch() {
        requisitionsHome.enterMyRequisitionsSearch("REQ");
        requisitionsHome.clickMyRequisitionsSearchButton();
    }

    @TestRailCase(selfReporting = true, automationId="1004")
    @Test(priority = 13, enabled = true)
    public void allRequisitionsTestSearch() {
        requisitionsHome.enterAllRequisitionsSearch("REQ");
        requisitionsHome.clickAllRequisitionsSearchButton();
    }

    @TestRailCase(selfReporting = true, automationId="1006")
    @Test(priority = 14, enabled = false)
    public void assignedToMeTestSearch() {
        requisitionsHome.enterAssignedToMeSearch("REQ");
        requisitionsHome.clickAssignedToMeSearchButton();
    }

    @TestRailCase(selfReporting = true, automationId="1005")
    @Test(priority = 15, enabled = false)
    public void assignedToAlternateTestSearch() {
        requisitionsHome.enterAssignedToAlternateSearch("REQ");
        requisitionsHome.clickAssignedToAlternateSearchButton();
    }
*/
    @TestRailCase(selfReporting = true, automationId="1007")
    @Test(priority = 16, enabled = true)
    public void createRequisitionsType() {
        newType = "Test" + num;
        menu.clickSettingsMenu();
        requisitionsHome.selectRequisitionTypesOption();
        requisitionsTypes.clickAddType();
        requisitionsAddType.enterName(newType);
        requisitionsAddType.enterDescription("desc" + newType);
        requisitionsAddType.enterPrefix("T" + num);
        requisitionsAddType.clickActive();
        requisitionsAddType.clickInstallLocationRequired();
        requisitionsAddType.clickAdminCanCreate();
        requisitionsAddType.clickAdminCanView();
        requisitionsAddType.clickInstallationType1();
        requisitionsAddType.clickInstallationType2();
        requisitionsAddType.clickDestinationType1();
        requisitionsAddType.clickDestinationType2();
        requisitionsAddType.clickSourceStatusCheckbox1();
        requisitionsAddType.clickSubmit();
    }

/*
    @TestRailCase(selfReporting = true, automationId="1015")
    @Test(priority = 17, enabled = true)
    public void editRequisitionsType() {
        requisitionsTypes.clickType("Engineering");
        requisitionsAddType.clickEdit();
        requisitionsAddType.clickSubmit();
    }


    @TestRailCase(selfReporting = true, automationId="1014")
    @Test(priority = 18, enabled = true)
    public void copyRequisitionsType() {
        newType = "Copy" + num;
        menu.clickSettingsMenu();
        requisitionsHome.selectRequisitionTypesOption();
        requisitionsTypes.clickType("Engineering");
        requisitionsAddType.clickCopy();
        requisitionsAddType.enterName(newType);
        requisitionsAddType.enterPrefix("C" + num);
        requisitionsAddType.clickSubmit();
    }

    @TestRailCase(selfReporting = true, automationId="1013")
    @Test(priority = 19, enabled = false)
    public void deleteRequisitionsType() {
        requisitionsTypes.clickType(newType);
        requisitionsAddType.clickEdit();
        requisitionsTypes.clickDelete();
    }
*/

    @TestRailCase(selfReporting = true, automationId="1008")
    @Test(priority = 20, enabled = true)
    public void createRequisition() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.create(needByDay, locationBU, project, job, shipToLocation, rushReason, installLocation, taskCode, 1);
    }
/*
    @TestRailCase(selfReporting = true, automationId="1008")
    @Test(priority = 21, enabled = true)
    public void createRequisition2() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.create(needByDay, locationBU, project, job, shipToLocation, rushReason, installLocation, taskCode, 1);
    }

    @TestRailCase(selfReporting = true, automationId="1008")
    @Test(priority = 22, enabled = true)
    public void createRequisition3() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.create(needByDay, locationBU, project, job, shipToLocation, rushReason, installLocation, taskCode, 1);
    }

    @TestRailCase(selfReporting = true, automationId="1008")
    @Test(priority = 23, enabled = true)
    public void createRequisition4() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.create(needByDay, locationBU, project, job, shipToLocation, rushReason, installLocation, taskCode, 1);
    }

    @TestRailCase(selfReporting = true, automationId="1059")
    @Test(priority = 24, enabled = true)
    public void reviewSubmittedRequisition() {
        requisitionsHome.clickReviewYourOrder();
    }

    @TestRailCase(selfReporting = true, automationId="1023")
    @Test(priority = 25, enabled = true)//, dependsOnMethods = "createRequisition")
    public void recreateRequisition() throws InterruptedException {
        requisitionsHome.clickRecreateButton();
        requisitionsHome.clickConfirmRecreateButton();
        reqsCreate.clickSubmitForApprovalButton();
        reqsCreate.clickComfirmSubmitButton();
    }
/*
    @TestRailCase(selfReporting = true, automationId="1055")
    @Test(priority = 26, enabled = true)//, dependsOnMethods = "createRequisition")
    public void approveRequisition() throws InterruptedException {
        menu.logout();
        login.login(driver.wd, title, "SLIBBY", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickApproveButton();
    }


    @TestRailCase(selfReporting = true, automationId="1011")
    @Test(priority = 27, enabled = true)//, dependsOnMethods = "createRequisition2")
    public void rejectRequisition() throws InterruptedException {
        menu.logout();
        login.login(driver.wd, title, "SLIBBY", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickRejectButton();
        requisitions.clickConfirmRejectButton();
    }

    @TestRailCase(selfReporting = true, automationId="1010")
    @Test(priority = 28, enabled = true)//, dependsOnMethods = "createRequisition3")
    public void alternateApproveRequisition() throws InterruptedException {
        menu.logout();
        login.login(driver.wd, title, "ato", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickApproveButton();
    }


    @TestRailCase(selfReporting = true, automationId="1012")
    @Test(priority = 29, enabled = true)//, dependsOnMethods = "createRequisition4")
    public void alternateRejectRequisition() throws InterruptedException {
        menu.logout();
        login.login(driver.wd, title, "ato", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickRejectButton();
        requisitions.clickConfirmRejectButton();
    }

    @TestRailCase(selfReporting = true, automationId="1055")
    @Test(priority = 30, enabled = true)
    public void reviewRejectedRequisition() {
        requisitionsHome.clickReviewRejectedOrder();
    }


    @TestRailCase(selfReporting = true, automationId="1008")
    @Test(priority = 31, enabled = false)
    public void createRequisition5() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.selectBusinessUnitDropDown("BOSTON");
        reqsCreate.selectProjectDropDown("7701NB16001 | 745 BOYLSTON ST - AXE83QLB");
        reqsCreate.enterJob("TBS 0120154840R-10");
        reqsCreate.selectShipToDropDown("BOS-WAREHOUSE | 89 FULKERSON ST");
        //reqsCreate.waitNeedByDate();
        reqsCreate.clickNeedByDate();
        reqsCreate.selectDate("29");
        reqsCreate.selectRushReasonDropDown("Re-Engineered Order");
        reqsCreate.clickAlternateRadioButton();
        reqsCreate.enterAddress1Field("12345 67th Ave NE");
        reqsCreate.enterAddress2Field("Suite 89");
        reqsCreate.enterCityTextField("Seattle");
        reqsCreate.enterPostalcodeField("98125");
        reqsCreate.clickNext();
        reqsCreate.clickAddLocationButton();
        reqsCreate.clickAddLocationSearchButton();
        reqsCreate.selectLocation("CL ABHGMIMNH25");
        reqsCreate.clickAddItems();
        reqsCreate.clickPartSearchButton();
        reqsCreate.selectTaskPartLine1("CL112");
        reqsCreate.enterQtyPartLine1(1);
        // reqsCreate.selectTaskPartLine4("CL112");
        // reqsCreate.enterQtyPartLine4(5);
        reqsCreate.clickPartSearchCloseButton();
        reqsCreate.clickSubmitForApprovalButton();
        reqsCreate.clickComfirmSubmitButton();
    }

    @TestRailCase(selfReporting = true, automationId="1049")
    @Test(priority = 32, enabled = false)
    public void validateHelpPage() throws InterruptedException {
        menu.clickHelpMenu();
        menu.selectHelpOption();
        System.out.println(driver.wd.getCurrentUrl());
    }



    @TestRailCase(selfReporting = true, automationId="1025")
    @Test(priority = 33, enabled = true)
    public void viewAlternateApproversPage() throws InterruptedException {
        menu.clickSettingsMenu();
        requisitionsHome.selectAlternateApproversOption();

    }



    @TestRailCase(selfReporting = true, automationId="1060")
    @Test(priority = 34, enabled = true)
    public void viewUserAlternateApproverPage() throws InterruptedException {
        menu.clickProfileMenu();
        requisitionsHome.selectAlternateApproverOption();
    }

    @TestRailCase(selfReporting = true, automationId="1024")
    @Test(priority = 35, enabled = true)
    public void updateAlternateApprover() throws InterruptedException {
        reqsAlternateApprover.enterAlternateApprover("MIKE PARKER (MPARKER)");
        reqsAlternateApprover.clickUpdateButton();
        reqsAlternateApprover.validateNewUserUpdate();
    }

    @TestRailCase(selfReporting = true, automationId="1046")
    @Test(priority = 36, enabled = true)
    public void updateSameAlternateApprover() throws InterruptedException {
        reqsAlternateApprover.clickUpdateButton();
        reqsAlternateApprover.validateSameUserUpdate();
    }

    @TestRailCase(selfReporting = true, automationId="1061")
    @Test(priority = 37, enabled = true)
    public void updateInvalidAlternateApprover() throws InterruptedException {
        reqsAlternateApprover.enterAlternateApprover("INVALID USER");
        reqsAlternateApprover.clickUpdateButton();
        reqsAlternateApprover.validateInvalidUserUpdate();
    }

    @TestRailCase(selfReporting = true, automationId="1024")
    @Test(priority = 38, enabled = true)
    public void updateAlternateApprover2() throws InterruptedException {
        reqsAlternateApprover.enterAlternateApprover("AARON MYERS (AMYERS)");
        reqsAlternateApprover.clickUpdateButton();
        reqsAlternateApprover.validateNewUserUpdate();
    }

    @TestRailCase(selfReporting = true, automationId="1047")
    @Test(priority = 40, enabled = true)
    public void validateAlternateApproverResetButton() throws InterruptedException {
        reqsAlternateApprover.enterAlternateApprover("TEST RESET BUTTON");
        reqsAlternateApprover.clickResetButton();
        reqsAlternateApprover.validateReset("AARON MYERS (AMYERS)");
    }

    @TestRailCase(selfReporting = true, automationId="1062")
    @Test(priority = 41, enabled = true)
    public void validateManageKitsPage() throws InterruptedException {
        menu.logout();
        login.login(driver.wd, title, "catsadm", "catscats11");
        menu.clickProfileMenu();
        requisitionsHome.selectManageKitsOption();
    }


    @TestRailCase(selfReporting = true, automationId="1042")
    @Test(priority = 42, enabled = true)
    public void navigatePublicKitsTab() throws InterruptedException {
        menu.clickProfileMenu();
        requisitionsHome.selectManageKitsOption();
        reqsKits.clickPublicKitsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1043")
    @Test(priority = 43, enabled = true)
    public void navigateMyKitsTab() throws InterruptedException {
        reqsKits.clickMyKitsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1044")
    @Test(priority = 44, enabled = true)
    public void navigateInactiveKitsTab() throws InterruptedException {
        reqsKits.clickInactiveKitsTab();
    }

    @TestRailCase(selfReporting = true, automationId="1045")
    @Test(priority = 45, enabled = true)
    public void searchKit() throws InterruptedException {
        reqsKits.clickMyKitsTab();
        reqsKits.enterKitSearch("Test");
    } */

    @Test(priority = 10000, enabled = true)
    public void logout() {
        menu.logout();
    }
}
