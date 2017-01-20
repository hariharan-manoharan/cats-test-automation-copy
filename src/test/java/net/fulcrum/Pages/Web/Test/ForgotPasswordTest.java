package net.fulcrum.Pages.Web.Test;

import java.net.MalformedURLException;

import net.fulcrum.Pages.Web.*;

import net.fulcrum.Pages.Web.*;

import net.fulcrum.Drivers.*;
import net.fulcrum.testrail.TestRailListener;
import net.fulcrum.testrail.annotations.TestRailCase;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;
import org.testng.Assert;


public class ForgotPasswordTest extends MainTest {

    protected ForgotPasswordPage forgotPassword = new ForgotPasswordPage(driver);
    protected String userName = "CATSADM";

    @Test(priority=1)
    public void forgotPassword()
    {
        login.validateTitle(driver.wd);
        forgotPassword.clickForgotPasswordLink();
    }

    @Test(priority=2)
    public void submitRequest()
    {
        forgotPassword.enterUserName(userName);
        forgotPassword.clickEmailResetLink();
    }


    /*@Test(priority=3)
    public void validateReturn()
    {
        forgotPassword.validateTitle(driver, "CATS CenterPoint: ");
    }*/
}
