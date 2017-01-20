package net.fulcrum.Pages.Web.Test;

import net.fulcrum.Pages.Web.Requisitions.RequisitionsHomePage;
import net.fulcrum.Pages.Web.Requisitions.RequisitionsPage;
import net.fulcrum.Pages.Web.Requisitions.RequisitionsCreatePage;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class RequisitionsCreateTest extends MainTest {

    private final String title = "CATS Requisitions:";
    private final String application = "requisitions";

    @BeforeMethod(alwaysRun = true)
    public void StageData() {
        requisitionsHome  = new RequisitionsHomePage(driver, url);
        requisitions  = new RequisitionsPage(driver, url);
        reqsCreate = new RequisitionsCreatePage(driver);
    }

    @TestRailCase(selfReporting = true, automationId="100")
    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.wd.get(url + application);
        login.login(title, userName, passWord);
    }

    @Test(priority = 2, enabled = true)
    public void validatePage() {
        requisitions.validatePage();
    }

    @Test(priority = 3, enabled = true)
    public void createRequisition() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.selectBusinessUnitDropDown("BOSTON |");
        reqsCreate.selectProjectDropDown("7701LN15011 | DWDM EQUIPMENT-BOSTON 2015");
        reqsCreate.enterJob("TBS 10944325");
        reqsCreate.selectShipToDropDown("BOS-WAREHOUSE | 89 FULKERSON ST");
        //reqsCreate.waitNeedByDate();
        reqsCreate.clickNeedByDate();
        reqsCreate.selectDate("29");
        reqsCreate.selectRushReasonDropDown("Re-Engineered Order");
        reqsCreate.clickNext();
        reqsCreate.clickAddLocationButton();
        reqsCreate.clickAddLocationSearchButton();
        reqsCreate.selectLocation("CL ABHGMIMNH25");
        reqsCreate.clickAddItems();
        reqsCreate.clickPartSearchButton();
        reqsCreate.selectTaskPartLine1("TN206");
        reqsCreate.enterQtyPartLine1(5);
       // reqsCreate.selectTaskPartLine4("CL112");
       // reqsCreate.enterQtyPartLine4(5);
        reqsCreate.clickPartSearchCloseButton();
        reqsCreate.clickSubmitForApprovalButton();
        reqsCreate.clickComfirmSubmitButton();
    }

    @Test(priority = 4, enabled = true, dependsOnMethods = "createRequisition")
    public void approveRequisition() throws InterruptedException {
        menu.logout();
        login.login(title, "slibby", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickApproveButton();
    }

    @Test(priority = 5, enabled = true)
    public void createRequisition2() {
        requisitionsHome.clickCreateButton();
        requisitionsHome.selectRequisitionType("Engineering");
        reqsCreate.selectBusinessUnitDropDown("BOSTON |");
        reqsCreate.selectProjectDropDown("7701LN15011 | DWDM EQUIPMENT-BOSTON 2015");
        reqsCreate.enterJob("TBS 10944325");
        reqsCreate.selectShipToDropDown("BOS-WAREHOUSE | 89 FULKERSON ST");
        //reqsCreate.waitNeedByDate();
        reqsCreate.clickNeedByDate();
        reqsCreate.selectDate("29");
        reqsCreate.selectRushReasonDropDown("Re-Engineered Order");
        reqsCreate.clickNext();
        reqsCreate.clickAddLocationButton();
        reqsCreate.clickAddLocationSearchButton();
        reqsCreate.selectLocation("CL ABHGMIMNH25");
        reqsCreate.clickAddItems();
        reqsCreate.clickPartSearchButton();
        reqsCreate.selectTaskPartLine1("TN206");
        reqsCreate.enterQtyPartLine1(1);
        // reqsCreate.selectTaskPartLine4("CL112");
        // reqsCreate.enterQtyPartLine4(5);
        reqsCreate.clickPartSearchCloseButton();
        reqsCreate.clickSubmitForApprovalButton();
        reqsCreate.clickComfirmSubmitButton();
    }

    @Test(priority = 6, enabled = true, dependsOnMethods = "createRequisition2")
    public void rejectRequisition() throws InterruptedException {
        menu.logout();
        login.login(title, "slibby", "catscats11");
        requisitionsHome.clickFirstRequisitionToApprove();
        requisitions.clickRejectButton();
        requisitions.clickConfirmRejectButton();
    }


}
