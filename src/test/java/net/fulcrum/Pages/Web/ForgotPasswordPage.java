package net.fulcrum.Pages.Web;
import org.openqa.selenium.By;



import junit.framework.Assert;
import net.fulcrum.Drivers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.*;


public class ForgotPasswordPage  {

    private WebBrowserDriver driver;

	protected String url = "https://172.16.0.60:8443/cats/password-recovery";
    
    protected By forgotYourPasswordLink  = By.linkText("forgot your password?");
    protected By userNameField  = By.id("username");
    protected By loginButton = By.id("loginBtn");
    protected By emailResetLinkButton = By.id("loginBtn");

    public ForgotPasswordPage (WebBrowserDriver driver) {
        this.driver = driver;
    }

	public void validatePasswordRecoveryPage(WebDriver driver)
	{
		String currentURL= driver.getCurrentUrl();
		Assert.assertEquals(url, currentURL);
	}

    public void enterUserName (String username) {
        driver.sendText(userNameField,username);
    }

    public void clickEmailResetLink () {
        driver.click(emailResetLinkButton);
    }

    public void clickForgotPasswordLink()
    {
        driver.click(forgotYourPasswordLink);
        //loginButton.wait(30);
    }
}
