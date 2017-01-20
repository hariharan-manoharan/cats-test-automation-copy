package net.fulcrum.Pages.Web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


import junit.framework.Assert;
import net.fulcrum.Drivers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.*;
import org.apache.log4j.*;

public class LoginPage {

    private WebBrowserDriver driver;
    static Logger log = Logger.getLogger(LoginPage.class);
    
    protected By usernameField = By.xpath("//input");
    protected By passwordField = By.xpath("//input[2]");
    protected By loginButton = By.id("loginBtn");
    protected By forgotYourPasswordLink = By.linkText("forgot your password?");
	protected By errorPath = By.cssSelector("P");

    private String url;

    public LoginPage (WebBrowserDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void enterUsername(String username) throws InterruptedException {
        driver.sendText(usernameField, username);
        log.info("Entered username: " + username);

    }

    public void enterPassword(String password) throws InterruptedException {
        driver.sendText(passwordField, password);
        log.info("Entered password: " + password);
    }

	public void clickLoginButton() {
        driver.click(loginButton);
        log.info("Clicked Login Button");
	}

    public void login(String title, String userName, String passWord) throws InterruptedException {
        //validateTitle(driver);
        try {
            enterUsername(userName);
            enterPassword(passWord);
        } catch (InterruptedException e) {
            System.out.println("Error entering username or password");
        }
        clickLoginButton();
    }


    public void validateTitle(WebDriver driver) {
        String getTitle = driver.getTitle();
        Assert.assertEquals("CATS CenterPoint:", getTitle);
        log.info("Validating Title: " + getTitle);
    }

    public void waitPageLoad() {
		//loginButton.wait(10);
	}

    public void validateErrorMessage() {
		String error = driver.getElement(errorPath).getText();
        log.info("Validating error message: " + error);
        Assert.assertEquals(error, "Bad credentials");
	}

    public void validateHeader() {
        Assert.assertEquals("Sign In", driver.getElement(By.cssSelector("form[name=\"loginForm\"] > h4")).getText());
        log.info("Validating header: Sign In");
    }

    public String getURL() {
        return url;
    }


}
