package net.fulcrum.Pages.Web.Test;
import net.fulcrum.Pages.Web.QueryBuilder.QueryBuilderHomePage;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class MenuTest extends MainTest {

    protected static QueryBuilderHomePage qb;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        qb = new QueryBuilderHomePage(driver);
    }

    @Test(priority=1, enabled=true)
    public void login() throws InterruptedException
    {
        login.validateTitle(driver.wd);
        login.enterUsername(userName);
        login.enterPassword(passWord);
        login.clickLoginButton();

    }

    @Test(priority=2, enabled=true)
    public void selectApplications()
    {
        menu.selectApplicationsOption();
    }

    @TestRailCase(selfReporting = true, automationId="2001")
    @Test(priority=4, enabled=true)
    public void selectRequisitions()
    {
        menu.selectRequisitionsOption();
        requisitions.validateTitle();
    }

    @TestRailCase(selfReporting = true, automationId="2002")
    @Test(priority=5, enabled=true)
    public void selectQueryBuilder()
    {
        menu.selectQueryBuilderOption();
        qb.validateTitle();
    }

    @TestRailCase(selfReporting = true, automationId="2003")
    @Test(priority=6, enabled=true)
    public void selectAdvancedInventory()
    {
        menu.selectAdvancedInventoryOption();
        advanced.validateTitle();

    }

    @TestRailCase(selfReporting = true, automationId="2004")
    @Test(priority=7, enabled=true)
    public void selectCenterPoint()
    {
        menu.selectCenterPointOption();
        centerPoint.validateTitle(driver.wd);

    }

    @TestRailCase(selfReporting = true, automationId="2005")
    @Test(priority=8, enabled=true)
    public void selectAbout()
    {
        menu.selectAboutOption();
        menu.clickClose();
    }

    @TestRailCase(selfReporting = true, automationId="2006")
    @Test(priority=10, enabled=false)
    public void selectOnlineHelp()
    {
        menu.selectHelpOption();
    }


    @TestRailCase(selfReporting = true, automationId="2007")
    @Test(priority=11, enabled=true)
    public void logout()
    {
        menu.logout();
    }
}
