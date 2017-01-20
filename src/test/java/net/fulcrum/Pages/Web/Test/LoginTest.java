package net.fulcrum.Pages.Web.Test;

import java.net.MalformedURLException;

import net.fulcrum.Pages.Web.*;
import net.fulcrum.Pages.Web.CenterPoint.LocationDetails;
import org.openqa.selenium.WebDriver;
import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;
import org.testng.Reporter;


@Listeners(TestRailListener.class)
public class LoginTest extends MainTest {

    String application = "cats";

    @BeforeClass(alwaysRun = true)
    public void StageData() {
        driver.wd.get(login.getURL() + application);
    }


    @TestRailCase(selfReporting = true, automationId="1001")
    @Test(priority=1001)
    public void validateSignInText ()
    {
        login.validateHeader();
        System.out.println("Validated the header of the Login page");
        Reporter.log("Validated the header of the Login page");
    }

    @TestRailCase(selfReporting = true, automationId="1002")
	@Test(priority=1002)
	public void invalidPassword() throws InterruptedException
	{
        Reporter.log("Logging with the following credentials username: " + userName
                + " password:Invalid Password");
        login.enterUsername(userName);
        login.enterPassword("Invalid Password");
		login.clickLoginButton();
        Reporter.log("Validated user cannot log in with invalid password");
	}

    @TestRailCase(selfReporting = true, automationId="1003")
    @Test(priority=1003)
    public void invalidUsername() throws InterruptedException
    {
        Reporter.log("Logging with the following credentials username: Invalid Username password: " + passWord);
        login.enterUsername("Invalid Username");
        login.enterPassword(passWord);
        login.clickLoginButton();
        Reporter.log("Validated user cannot log in with invalid username");
    }

    @TestRailCase(selfReporting = true, automationId="1004")
    @Test(priority=1004)
    public void invalidUsernamePassword () throws InterruptedException {
        Reporter.log("Logging with the following credentials username: " + userName
                + " password: " + passWord);
        login.enterUsername("Invalid Username");
        login.enterPassword("Invalid Password");
        login.clickLoginButton();
        Reporter.log("Validated user cannot log in with invalid username and password");
    }

    @TestRailCase(selfReporting = true, automationId="1005")
    @Test(priority=1005)
    public void validLogin() throws InterruptedException {
        Reporter.log("Logging with the following credentials username: " + userName
                + " password: " + passWord);
        login.enterUsername(userName);
        login.enterPassword(passWord);
        login.clickLoginButton();
        Reporter.log("Validated user can log in with valid credentials");

    }

}
