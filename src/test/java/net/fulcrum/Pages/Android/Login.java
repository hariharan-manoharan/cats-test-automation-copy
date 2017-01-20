package net.fulcrum.Pages.Android;

import net.fulcrum.Drivers.AndroidMobilityDriver;
import org.openqa.selenium.By;

public class Login extends AndroidPageMain  {

    protected By usernameField = By.id("net.fulcrum.mobility:id/username");
    protected By passwordField = By.id("net.fulcrum.mobility:id/password");
    protected By connectButton = By.id("net.fulcrum.mobility:id/btn_connect");
    protected By addConnectionDropDown = By.id("net.fulcrum.mobility:id/my_connections");
    protected By rememberMeCheckBox = By.id("net.fulcrum.mobility:id/my_connections");
    protected By infoButton = By.id("net.fulcrum.mobility:id/info_button");

    protected By helpButton = By.id("//android.widget.LinearLayout[1]/android.widget.FrameLayout[1]/" +
            "android.widget.LinearLayout[1]/android.webkit.WebView[1]/android.webkit.WebView[1]/" +
            "android.view.View[1]/android.view.View[1]");


    public Login (AndroidMobilityDriver driver) {
        this.driver = driver;
    }

    public void enterUsername (String username) {
        driver.sendKeys(usernameField, username);
    }

    public void enterPassword (String password) {
        driver.sendKeys(passwordField, password);
    }

    public void clickConnect () {
        driver.click(connectButton);
    }

    public void addConnection () {
        driver.click(addConnectionDropDown);
    }

    public void clickRememberMe() {
        driver.click(rememberMeCheckBox);
    }

    public void clickInfo() {
        driver.click(infoButton);
    }
}
