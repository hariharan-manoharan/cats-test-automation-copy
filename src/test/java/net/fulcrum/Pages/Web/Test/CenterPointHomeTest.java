package net.fulcrum.Pages.Web.Test;


import net.fulcrum.Drivers.WebBrowserDriver;
import net.fulcrum.Pages.Web.CenterPoint.CenterPointHomePage;
import net.fulcrum.Pages.Web.CenterPoint.LocationDetails;
import net.fulcrum.testrail.annotations.TestRailCase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class CenterPointHomeTest extends MainTest {


    protected String title = "CATS CenterPoint:";
    protected String application = "cats";
    protected LocationDetails locationDetails;

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        locationDetails = new LocationDetails(driver);
    }

    @Test(priority = 1)
    public void login() throws InterruptedException
    {
        driver.wd.get(url + application);
        login.login(title, userName, passWord);
    }


    @Test(priority = 2, enabled = true)
    public void validatePage()
    {
        locationDetails.validatePage(driver.wd);
    }

    @TestRailCase(automationId = "9001")
    @Test(priority = 3, enabled = true)
    public void navigateToDataforms()
    {
        locationDetails.navigateToDataforms();
    }

    @TestRailCase(automationId = "9002")
    @Test(priority = 4, enabled = true)
    public void search()
    {
        locationDetails.enterSearch("Location");
    }


    @TestRailCase(automationId = "9003")
    @Test(priority = 5, enabled = true)
    public void clickUsersDataForm()
    {
        locationDetails.clickDataform("Location Details");
    }

    @TestRailCase(automationId = "9006")
    @Test(priority = 6, enabled = true)
    public void clickEditTab()
    {
        locationDetails.clickEditTab();
    }

    @TestRailCase(automationId = "9004")
    @Test(priority = 7, enabled = true)
    public void clickResultsTab()
    {
        locationDetails.clickResultsTab();
    }

    @TestRailCase(automationId = "9005")
    @Test(priority = 8, enabled = true)
    public void clickSearchTab()
    {
        locationDetails.clickSearchTab();
    }

    @TestRailCase(automationId = "9007")
    @Test(priority = 9, enabled = true)
    public void performLocationSearch()
    {
        locationDetails.clickSearchTab();
        locationDetails.enterField("L.NAME", "1MG7417");
        locationDetails.clickSearchButton();
    }

    @TestRailCase(automationId = "9008")
    @Test(priority = 10, enabled = true)
    public void navigateToMoreResults()
    {
        locationDetails.clickResultsTabNextButton();
    }

    @TestRailCase(automationId = "9009")
    @Test(priority = 11, enabled = true)
    public void clearResults()
    {
        locationDetails.clearResults();
    }


    @TestRailCase(automationId = "9010")
    @Test(priority = 12, enabled = true)
    public void openWebReportsFolder()
    {
        locationDetails.clickWebReports();
    }

}
