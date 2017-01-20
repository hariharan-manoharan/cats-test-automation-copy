package net.fulcrum.Pages.Web.Test;

import net.fulcrum.Pages.Web.QueryBuilder.QueryBuilderHomePage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.testng.annotations.*;

@Listeners(TestRailListener.class)
public class QueryBuilderHomeTest extends MainTest {

    private String title = "CATS Query Builder";
    private final static String application = "catsqb";

    private static QueryBuilderHomePage qb;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        qb = new QueryBuilderHomePage(driver);
        wd.get(url + application);
    }

    @TestRailCase(automationId = "1001")
    @Test(priority = 1)
    public void login() throws InterruptedException {
        login.enterUsername(userName);
        login.enterPassword(passWord);
        login.clickLoginButton();
    }

    @Test(priority = 2, enabled = false)
    public void validatePage() {
        qb.validateTitle();
    }

    @TestRailCase(automationId = "1002")
    @Test(priority = 3, enabled = true)
    public void clickSchedule() {
        qb.clickSchedulesTab();
    }

    @TestRailCase(automationId = "1003")
    @Test(priority = 5, enabled = true)
    public void clickQueries() {
        qb.clickQueriesTab();
    }

    @TestRailCase(automationId = "1004")
    @Test(priority = 4, enabled = true)
    public void clickHistory() {
        qb.clickHistoryTab();
    }

    @TestRailCase(automationId = "1005")
    @Test(priority = 6, enabled = true)
    public void clickAssetsView() {
        qb.clickQueriesTab();
        qb.clickAssetsViewLink();
    }


}
