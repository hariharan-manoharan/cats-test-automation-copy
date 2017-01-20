package net.fulcrum.Pages.Web.Test;

import net.fulcrum.Pages.Web.Requisitions.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import java.util.Random;

import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class RequisitionsAddTypeTest extends MainTest {

    protected String title = "CATS Requisitions:";
    protected String application = "requisitions";


    @BeforeMethod(alwaysRun = true)
    public void stageData() {
        requisitionsHome  = new RequisitionsHomePage(driver, url);
        requisitionsAddType  = new RequisitionsAddTypePage(driver);
        requisitionsTypes = new RequisitionsTypesPage(driver);
        reqsCreate = new RequisitionsCreatePage(driver);
    }

    @Test(priority = 1)
    public void login() throws InterruptedException {
        driver.wd.get(url + application);
        login.login(title, userName, passWord);
    }

    @Test(priority = 2, enabled = true)
    public void createRequisitionsType() {
        menu.clickSettingsMenu();
        requisitionsHome.selectRequisitionTypesOption();
        requisitionsTypes.clickAddType();
        Random rand = new Random();
        Integer num = rand.nextInt(10000) + 1;
        System.out.println("Test" + num);
        requisitionsAddType.enterName("Test" + num);
        requisitionsAddType.enterDescription("desc" + num);
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


    @Test(priority = 3, enabled = true)
    public void editRequisitionsType() {
        requisitionsTypes.clickType("Engineering");
        requisitionsAddType.clickEdit();
        requisitionsAddType.clickSubmit();
    }



    @Test(priority = 4, enabled = true)
    public void copyRequisitionsType() {
        Random rand = new Random();
        Integer num = rand.nextInt(10000) + 1;
        menu.clickSettingsMenu();
        requisitionsHome.selectRequisitionTypesOption();
        requisitionsTypes.clickType("Engineering");
        requisitionsAddType.clickCopy();
        requisitionsAddType.enterName("Copy" + num);
        requisitionsAddType.enterPrefix("T" + num);
        requisitionsAddType.clickSubmit();
    }

    @Test(priority = 5, enabled = true)
    public void deleteRequisitionsType() {
        requisitionsTypes.clickDelete();
    }



}
